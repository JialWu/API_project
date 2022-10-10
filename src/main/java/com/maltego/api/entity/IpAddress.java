package com.maltego.api.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class IpAddress {

    @Id
    private int id;

    private String ipAddress;

    @OneToOne()
    @JoinColumn(name = "location_id", insertable = false)
    private Location location;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ip_id")
    private Set<Data> data;

    public IpAddress() {
    }

    public IpAddress(int id, String ipAddress, Location location, Set<Data> data) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.location = location;
        this.data = data;
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

    public Set<Data> getData() {
        return data;
    }

    public void setData(Set<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", location=" + location +
                ", data=" + data +
                '}';
    }
}
