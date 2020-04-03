package com.home.test.persist.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.UUID;

public class ListKeyspaces {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int port = 30942;
        String keyspace = "system";

        System.out.println("Creating cluster");
        Cluster cluster = Cluster.builder()
                .addContactPoints(serverIP)
                .withPort(port)
                .build();

        System.out.println("Creating session...");
        Session session = cluster.connect(keyspace);
        System.out.println("created");
        String catalogKeySpace = "catalog";
        String catalogKeySpaceTable = "catalog";
        String cqlStatement = "SELECT * FROM system_schema.tables WHERE keyspace_name = '"+ catalogKeySpace + "';";
        for (Row row : session.execute(cqlStatement)) {
            System.out.println(row.toString());
        }

        System.out.println("Inserting record1");
        String insert1 = "INSERT INTO catalog.catalog (catalogId, name) values (" + UUID.randomUUID()+", 'name1')";
        System.out.println("Closing session");
        System.out.println("Inserting record2");
        String insert2 = "INSERT INTO catalog.catalog (catalogId, name) values (" + UUID.randomUUID()+", 'name2')";
        System.out.println("Closing session");
        System.out.println("Inserting record3");
        String insert3 = "INSERT INTO catalog.catalog (catalogId, name) values (" + UUID.randomUUID()+", 'name3')";

        session.execute(insert1);
        session.execute(insert2);
        session.execute(insert3);
        System.out.println("Closing session");
        session.close();
        System.out.println("Closed");
        System.out.println("Closing cluster");
        cluster.close();
        System.out.println("closed");
    }
}
