package com.maltego.api.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class RecordData {

    @Id
    private int dataId;

    private String ipAddress;

    @ElementCollection
    private List<Integer> abuseCategories = new ArrayList<Integer>();

    public RecordData() {
    }

    public RecordData(int dataId, String ipAddress, List<Integer> abuseCategories) {
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
        return "RecordData{" +
                "dataId=" + dataId +
                ", ipAddress='" + ipAddress + '\'' +
                ", abuseCategories=" + abuseCategories +
                '}';
    }
}
