package com.maltego.api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class IpAddress {

    @Id
    private int id;

    private String ipAddress;

    @OneToOne()
    @JoinColumn(name = "location_id", insertable = false)
    private Location location;

    @OneToMany(mappedBy = "ipAddress")
    private List<AbuseRecords> recordsList;

    public IpAddress() {
    }

    public IpAddress(int id, String ipAddress, Location location, List<AbuseRecords> recordsList) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.location = location;
        this.recordsList = recordsList;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<AbuseRecords> getRecordsList() {
        return recordsList;
    }

    public void setRecordsList(List<AbuseRecords> recordsList) {
        this.recordsList = recordsList;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", location=" + location +
                ", recordsList=" + recordsList +
                '}';
    }
}
