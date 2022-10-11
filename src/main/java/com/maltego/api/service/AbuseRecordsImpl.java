package com.maltego.api.service;

import com.maltego.api.entity.Data;
import com.maltego.api.repository.AbuseRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AbuseRecordsImpl implements AbuseRecordsService{

    @Autowired
    private AbuseRecordsRepository abuseRecordsRepository;

    @Override
    public Data createRecord(Data data) {
        try {
            data.setDataId(null == abuseRecordsRepository.findMaxId()? 0 : abuseRecordsRepository.findMaxId() + 1);
            abuseRecordsRepository.save(data);
            return data;
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    public Set<Data> getAbuseRecords(String ipAddress) {
        try {
            return abuseRecordsRepository.findAbuseRecords(ipAddress);
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    public List<Data> getAbuseRecordsByCategory(String ipAddress, Integer category) {
        try {
            Set<Data> allRecordsOfIp = abuseRecordsRepository.findAbuseRecords(ipAddress);
            List<Data> recordsByCategory = new ArrayList<Data>();

            allRecordsOfIp.stream().forEach(record -> {
                if (record.getAbuseCategories().iterator().next() == category) {
                    recordsByCategory.add(record);
                }
            });
            return recordsByCategory;
        } catch(Exception e) {
            throw e;
        }
    }
}
