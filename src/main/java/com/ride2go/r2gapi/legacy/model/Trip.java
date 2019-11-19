
package com.ride2go.r2gapi.legacy.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trip {

    private String tripID;
    private String iDuser;
    private String startdate;
    private String starttime;
    private String starttimeSearchRoute;
    private String smoker;
    private Object prefgender;
    private Object numberPlate;
    private String places;
    private String enterdate;
    private Object contactmail;
    private Object contactlandline;
    private Object contactmobile;
    private String description;
    private Object clientIP;
    private String triptype;
    private Object partnername;
    private Object deeplink;
    private String relevance;
    private Object price;
    private String currency;
    private Object priceSearchRoute;
    private String missingreoccurs;
    private Object premiumuserUntil;
    private Object businessuserUntil;
    private Object adacuser;
    private Object acauser;
    private Object acluser;
    private Object tcsuser;
    private Object touringuser;
    private String fromTitle;
    private String fromSeotitle;
    private String toTitle;
    private String toSeotitle;
    private Object car;
    private Object baggage;
    private Object animals;
    private String intId;
    private Object url;
    private Object reminderLastSend;
    private String reminderSendIds;
    private String reminderActive;
    private Object accuracy;
    private List<Routing> routings = null;
    private Reoccur reoccur;
    private Privacy privacy;
    private Long likes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getIDuser() {
        return iDuser;
    }

    public void setIDuser(String iDuser) {
        this.iDuser = iDuser;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStarttimeSearchRoute() {
        return starttimeSearchRoute;
    }

    public void setStarttimeSearchRoute(String starttimeSearchRoute) {
        this.starttimeSearchRoute = starttimeSearchRoute;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public Object getPrefgender() {
        return prefgender;
    }

    public void setPrefgender(Object prefgender) {
        this.prefgender = prefgender;
    }

    public Object getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(Object numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public Object getContactmail() {
        return contactmail;
    }

    public void setContactmail(Object contactmail) {
        this.contactmail = contactmail;
    }

    public Object getContactlandline() {
        return contactlandline;
    }

    public void setContactlandline(Object contactlandline) {
        this.contactlandline = contactlandline;
    }

    public Object getContactmobile() {
        return contactmobile;
    }

    public void setContactmobile(Object contactmobile) {
        this.contactmobile = contactmobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getClientIP() {
        return clientIP;
    }

    public void setClientIP(Object clientIP) {
        this.clientIP = clientIP;
    }

    public String getTriptype() {
        return triptype;
    }

    public void setTriptype(String triptype) {
        this.triptype = triptype;
    }

    public Object getPartnername() {
        return partnername;
    }

    public void setPartnername(Object partnername) {
        this.partnername = partnername;
    }

    public Object getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(Object deeplink) {
        this.deeplink = deeplink;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getPriceSearchRoute() {
        return priceSearchRoute;
    }

    public void setPriceSearchRoute(Object priceSearchRoute) {
        this.priceSearchRoute = priceSearchRoute;
    }

    public String getMissingreoccurs() {
        return missingreoccurs;
    }

    public void setMissingreoccurs(String missingreoccurs) {
        this.missingreoccurs = missingreoccurs;
    }

    public Object getPremiumuserUntil() {
        return premiumuserUntil;
    }

    public void setPremiumuserUntil(Object premiumuserUntil) {
        this.premiumuserUntil = premiumuserUntil;
    }

    public Object getBusinessuserUntil() {
        return businessuserUntil;
    }

    public void setBusinessuserUntil(Object businessuserUntil) {
        this.businessuserUntil = businessuserUntil;
    }

    public Object getAdacuser() {
        return adacuser;
    }

    public void setAdacuser(Object adacuser) {
        this.adacuser = adacuser;
    }

    public Object getAcauser() {
        return acauser;
    }

    public void setAcauser(Object acauser) {
        this.acauser = acauser;
    }

    public Object getAcluser() {
        return acluser;
    }

    public void setAcluser(Object acluser) {
        this.acluser = acluser;
    }

    public Object getTcsuser() {
        return tcsuser;
    }

    public void setTcsuser(Object tcsuser) {
        this.tcsuser = tcsuser;
    }

    public Object getTouringuser() {
        return touringuser;
    }

    public void setTouringuser(Object touringuser) {
        this.touringuser = touringuser;
    }

    public String getFromTitle() {
        return fromTitle;
    }

    public void setFromTitle(String fromTitle) {
        this.fromTitle = fromTitle;
    }

    public String getFromSeotitle() {
        return fromSeotitle;
    }

    public void setFromSeotitle(String fromSeotitle) {
        this.fromSeotitle = fromSeotitle;
    }

    public String getToTitle() {
        return toTitle;
    }

    public void setToTitle(String toTitle) {
        this.toTitle = toTitle;
    }

    public String getToSeotitle() {
        return toSeotitle;
    }

    public void setToSeotitle(String toSeotitle) {
        this.toSeotitle = toSeotitle;
    }

    public Object getCar() {
        return car;
    }

    public void setCar(Object car) {
        this.car = car;
    }

    public Object getBaggage() {
        return baggage;
    }

    public void setBaggage(Object baggage) {
        this.baggage = baggage;
    }

    public Object getAnimals() {
        return animals;
    }

    public void setAnimals(Object animals) {
        this.animals = animals;
    }

    public String getIntId() {
        return intId;
    }

    public void setIntId(String intId) {
        this.intId = intId;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getReminderLastSend() {
        return reminderLastSend;
    }

    public void setReminderLastSend(Object reminderLastSend) {
        this.reminderLastSend = reminderLastSend;
    }

    public String getReminderSendIds() {
        return reminderSendIds;
    }

    public void setReminderSendIds(String reminderSendIds) {
        this.reminderSendIds = reminderSendIds;
    }

    public String getReminderActive() {
        return reminderActive;
    }

    public void setReminderActive(String reminderActive) {
        this.reminderActive = reminderActive;
    }

    public Object getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Object accuracy) {
        this.accuracy = accuracy;
    }

    public List<Routing> getRoutings() {
        return routings;
    }

    public void setRoutings(List<Routing> routings) {
        this.routings = routings;
    }

    public Reoccur getReoccur() {
        return reoccur;
    }

    public void setReoccur(Reoccur reoccur) {
        this.reoccur = reoccur;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
