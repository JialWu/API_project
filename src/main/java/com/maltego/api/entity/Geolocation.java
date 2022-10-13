package com.maltego.api.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
@Entity
public class Geolocation {

    @Id
    private int id;

    private String ipAddress;

    @OneToOne()
    @JoinColumn(name = "location_id", insertable = false)
    private Location location;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private List<String> domain = new ArrayList<String>();

    @OneToOne()
    @JoinColumn(name = "as_id", insertable = false)
    private ASN as;

    private String isp;

    public Geolocation() {
    }

    public Geolocation(int id, String ipAddress, Location location, List<String> domain, ASN as, String isp) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.location = location;
        this.domain = domain;
        this.as = as;
        this.isp = isp;
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

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public ASN getAs() {
        return as;
    }

    public void setAs(ASN as) {
        this.as = as;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "Geolocation{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", location=" + location +
                ", domain=" + domain +
                ", as=" + as +
                ", isp='" + isp + '\'' +
                '}';
    }
}
