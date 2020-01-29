package com.ride2go.r2gapi;

import org.testcontainers.containers.MySQLContainer;

public class Ride2GoMySqlContainer extends MySQLContainer<Ride2GoMySqlContainer> {

    private static final String IMAGE_VERSION = "mysql:5.7";
    private static Ride2GoMySqlContainer container;

    private Ride2GoMySqlContainer() {
        super(IMAGE_VERSION);
    }

    public static Ride2GoMySqlContainer getInstance() {
        if (container == null) {
            container = new Ride2GoMySqlContainer()
                    .withDatabaseName("fgtest")
                    //.withInitScript("init-tc-schema.sql")
            ;
        }
        return container;
    }

    @Override
    public void start() {
        //DB_HOST=localhost;DB_NAME=fgtest;DB_USER=ride2go;DB_PASSWORD=ride2go123
        super.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("DB_NAME", container.getDatabaseName());
        System.setProperty("DB_USER", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
