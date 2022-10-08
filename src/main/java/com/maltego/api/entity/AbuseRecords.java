package com.maltego.api.entity;

import javax.persistence.*;

@Entity
public class AbuseRecords {

    @Id
    private int recordId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ipaddress_id")
    private IpAddress ipAddress;

    @OneToOne()
    @JoinColumn(name = "data_id", insertable = false)
    private RecordData recordData;

    public AbuseRecords() {
    }

    public AbuseRecords(int recordId, RecordData recordData) {
        this.recordId = recordId;
        this.recordData = recordData;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public RecordData getRecordData() {
        return recordData;
    }

    public void setRecordData(RecordData recordData) {
        this.recordData = recordData;
    }

    @Override
    public String toString() {
        return "AbuseRecords{" +
                "recordId=" + recordId +
                ", recordData=" + recordData +
                '}';
    }
}
