
package com.ride2go.r2gapi.legacy.model;

import java.util.HashMap;
import java.util.Map;

public class Routing {

    private Object routingID;
    private Origin origin;
    private Destination destination;
    private String routingIndex;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getRoutingID() {
        return routingID;
    }

    public void setRoutingID(Object routingID) {
        this.routingID = routingID;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getRoutingIndex() {
        return routingIndex;
    }

    public void setRoutingIndex(String routingIndex) {
        this.routingIndex = routingIndex;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
