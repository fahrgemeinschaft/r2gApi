package com.ride2go.r2gapi;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class Ride2GoElasticSearchContainer extends ElasticsearchContainer {

    private static final String IMAGE_VERSION = "elasticsearch:2.4.6";
    private static final int ORIGINAL_REST_PORT = 9200;
    private static Ride2GoElasticSearchContainer container;

    private Ride2GoElasticSearchContainer() {
        super(IMAGE_VERSION);
    }

    public static Ride2GoElasticSearchContainer getInstance() {
        if (container == null) {
            container = new Ride2GoElasticSearchContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();

        System.setProperty("R2G_LEGACY_ELASTIC_HOST", withoutPort(container.getHttpHostAddress()));
        System.setProperty("R2G_LEGACY_ELASTIC_PORT", String.valueOf(container.getMappedPort(ORIGINAL_REST_PORT)));
    }

    private String withoutPort(String host) {
        int colonIndex = host.lastIndexOf(':');
        if (colonIndex < 0) {
            return host;
        }

        return host.substring(0, colonIndex);
    }

}
