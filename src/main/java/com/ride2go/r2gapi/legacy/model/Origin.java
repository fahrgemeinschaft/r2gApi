
package com.ride2go.r2gapi.legacy.model;

import java.util.HashMap;
import java.util.Map;

public class Origin {

    private String placeID;
    private String address;
    private String accuracy;
    private Object localityName;
    private String countryCode;
    private String countryName;
    private String longitude;
    private String latitude;
    private Object postalCode;
    private String placetype;
    private Object stopPrice;
    private Object stopTime;
    private LatLonBox latLonBox;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public Object getLocalityName() {
        return localityName;
    }

    public void setLocalityName(Object localityName) {
        this.localityName = localityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public String getPlacetype() {
        return placetype;
    }

    public void setPlacetype(String placetype) {
        this.placetype = placetype;
    }

    public Object getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(Object stopPrice) {
        this.stopPrice = stopPrice;
    }

    public Object getStopTime() {
        return stopTime;
    }

    public void setStopTime(Object stopTime) {
        this.stopTime = stopTime;
    }

    public LatLonBox getLatLonBox() {
        return latLonBox;
    }

    public void setLatLonBox(LatLonBox latLonBox) {
        this.latLonBox = latLonBox;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
