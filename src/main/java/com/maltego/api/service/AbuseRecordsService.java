package com.maltego.api.service;

import com.maltego.api.entity.AbuseRecord;
import com.maltego.api.entity.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AbuseRecordsService {

    void createRecord(AbuseRecord abuseRecord);

    Set<AbuseRecord> getAbuseRecords(String ipAddress);

    List<AbuseRecord> getAbuseRecordsByCategory(String ipAddress, Integer category);
}
