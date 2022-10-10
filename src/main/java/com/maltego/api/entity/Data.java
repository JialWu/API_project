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
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int dataId;

    private String ipAddress;

    @Column(columnDefinition = "json")
    @Type(type = "json")
    private List<Integer> abuseCategories = new ArrayList<Integer>();

    public Data() {
    }

    public Data(int dataId, String ipAddress, List<Integer> abuseCategories) {
        this.dataId = dataId;
        this.ipAddress = ipAddress;
        this.abuseCategories = abuseCategories;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
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

    @Override
    public String toString() {
        return "Data{" +
                "dataId=" + dataId +
                ", ipAddress='" + ipAddress + '\'' +
                ", abuseCategories=" + abuseCategories +
                '}';
    }
}
