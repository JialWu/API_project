package com.maltego.api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AbuseRecord {
    @EmbeddedId
    private Data data;

    public AbuseRecord() {
    }

    public AbuseRecord(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AbuseRecord{" +
                "data=" + data +
                '}';
    }
}
