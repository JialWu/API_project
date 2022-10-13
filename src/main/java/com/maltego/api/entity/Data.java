package com.maltego.api.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
@Embeddable
public class Data implements Serializable {

    private String ipAddress;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private List<Integer> abuseCategories = new ArrayList<Integer>();

    @Column(name = "time")
    private LocalDateTime localDateTime;

    public Data() {
    }

    public Data(String ipAddress, List<Integer> abuseCategories, LocalDateTime localDateTime) {
        this.ipAddress = ipAddress;
        this.abuseCategories = abuseCategories;
        this.localDateTime = localDateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<Integer> getAbuseCategories() {
        return abuseCategories;
    }

    public void setAbuseCategories(List<Integer> abuseCategories) {
        this.abuseCategories = abuseCategories;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ipAddress='" + ipAddress + '\'' +
                ", abuseCategories=" + abuseCategories +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
