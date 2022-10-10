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

    public Location() {
    }

    public Location(int locationId, String country, String region) {
        this.locationId = locationId;
        this.country = country;
        this.region = region;
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

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
