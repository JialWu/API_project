package com.maltego.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Geolocation implements Serializable {

    @Id
    private int geolocationId;
    @OneToOne()
    @JoinColumn(name = "location_id", insertable = false)
    private Location location;

    public Geolocation() {
    }

    public int getGeolocationId() {
        return geolocationId;
    }

    public void setGeolocationId(int geolocationId) {
        this.geolocationId = geolocationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Geolocation{" +
                "id=" + geolocationId +
                ", location=" + location +
                '}';
    }
}
