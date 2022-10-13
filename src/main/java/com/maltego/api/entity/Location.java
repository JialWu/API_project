package com.maltego.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Location implements Serializable {

    @Id
    private int locationId;

    private String country;

    private String region;

    private String city;

    private Float lat;

    private Float Lng;

    private String postalCode;

    private String timeZone;

    public Location() {
    }

    public Location(int locationId, String country, String region, String city, Float lat, Float lng, String postalCode, String timeZone) {
        this.locationId = locationId;
        this.country = country;
        this.region = region;
        this.city = city;
        this.lat = lat;
        this.Lng = lng;
        this.postalCode = postalCode;
        this.timeZone = timeZone;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return Lng;
    }

    public void setLng(Float lng) {
        Lng = lng;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", lat=" + lat +
                ", Lng=" + Lng +
                ", postalCode='" + postalCode + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
