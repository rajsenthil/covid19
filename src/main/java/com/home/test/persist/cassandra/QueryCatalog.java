package com.home.test.persist.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.UUID;

public class QueryCatalog {
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
        String cqlStatement = "SELECT * FROM catalog.catalog;";
        for (Row row : session.execute(cqlStatement)) {
            System.out.println(row.toString());
        }

        session.close();
        System.out.println("Closed");
        System.out.println("Closing cluster");
        cluster.close();
        System.out.println("closed");
    }
}
