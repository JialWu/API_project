package com.maltego.api.entity;

import javax.persistence.*;

@Entity
public class Geolocation {

    @Id
    private int id;

    private String ipAddress;

    @OneToOne()
    @JoinColumn(name = "location_id", insertable = false)
    private Location location;

    public Geolocation() {
    }

    public Geolocation(int id, String ipAddress, Location location) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", location=" + location +
                '}';
    }
}
