package com.maltego.api.service;

import com.maltego.api.entity.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AbuseRecordsService {

    Data createRecord(Data data);

    Set<Data> getAbuseRecords(String ipAddress);

    List<Data> getAbuseRecordsByCategory(String ipAddress, Integer category);
}
