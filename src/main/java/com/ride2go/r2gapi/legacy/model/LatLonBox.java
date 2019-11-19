
package com.ride2go.r2gapi.legacy.model;

import java.util.HashMap;
import java.util.Map;

public class LatLonBox {

    private Object north;
    private Object south;
    private Object east;
    private Object west;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getNorth() {
        return north;
    }

    public void setNorth(Object north) {
        this.north = north;
    }

    public Object getSouth() {
        return south;
    }

    public void setSouth(Object south) {
        this.south = south;
    }

    public Object getEast() {
        return east;
    }

    public void setEast(Object east) {
        this.east = east;
    }

    public Object getWest() {
        return west;
    }

    public void setWest(Object west) {
        this.west = west;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
