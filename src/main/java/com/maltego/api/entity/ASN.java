package com.maltego.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ASN implements Serializable {

    @Id
    private int asId;

    private Integer asn;

    private String name;

    private String route;

    private String domain;

    public ASN() {
    }

    public ASN(int asId, Integer asn, String name, String route, String domain) {
        this.asId = asId;
        this.asn = asn;
        this.name = name;
        this.route = route;
        this.domain = domain;
    }

    public int getAsId() {
        return asId;
    }

    public void setAsId(int asId) {
        this.asId = asId;
    }

    public Integer getAsn() {
        return asn;
    }

    public void setAsn(Integer asn) {
        this.asn = asn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "As{" +
                "asId=" + asId +
                ", asn=" + asn +
                ", name='" + name + '\'' +
                ", route='" + route + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
