package com.home.test.persist.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int port = 30942;
        String keyspace = "system";

        Cluster cluster = Cluster.builder()
                .addContactPoints(serverIP)
                .withPort(port)
                .build();

        Session session = cluster.connect(keyspace);
        String cqlStatement = "SELECT * FROM local";
        for (Row row : session.execute(cqlStatement)) {
            System.out.println(row.toString());
        }

        String catalogKeySpace = "catalog";
        String catalogKeySpaceTable = "catalog";
        String createKS = "CREATE KEYSPACE "+catalogKeySpace+" WITH " +
                "replication = {'class':'SimpleStrategy','replication_factor':1}";

        session.execute(createKS);

        System.out.println("Session closing after creating keyspace catalog");
        session.close();
        System.out.println("Closed");

        System.out.println("Session Connecting to catalog keyspace");
        session = cluster.connect(catalogKeySpace);
        System.out.println("Connected");

        //Create ColumnFamily (aka table)
        System.out.println("Creating column family ");
        String createCatalogTable = "CREATE TABLE " + catalogKeySpaceTable + "( catalogId uuid primary key, name text )";

        System.out.println("Creating the table...");
        session.execute(createCatalogTable);
        System.out.println("Table created");

        System.out.println("Session closing...");
        session.close();
        System.out.println("Closed");

        System.out.println("Cluster closing...");
        cluster.close();
        System.out.println("Closed.");


    }
}
