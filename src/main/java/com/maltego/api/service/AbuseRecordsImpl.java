package com.maltego.api.service;

import com.maltego.api.entity.AbuseRecord;
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
    public void createRecord(AbuseRecord abuseRecord) {
        try {
            //data.setDataId(null == abuseRecordsRepository.findMaxId()? 0 : abuseRecordsRepository.findMaxId() + 1);
            abuseRecordsRepository.save(abuseRecord);
            //return abuseRecord;
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    public Set<AbuseRecord> getAbuseRecords(String ipAddress) {
        try {
            return abuseRecordsRepository.findAbuseRecords(ipAddress);
        } catch(Exception e) {
            throw e;
        }
    }

    @Override
    public List<AbuseRecord> getAbuseRecordsByCategory(String ipAddress, Integer category) {
        try {
            Set<AbuseRecord> allRecordsOfIp = abuseRecordsRepository.findAbuseRecords(ipAddress);
            List<AbuseRecord> recordsByCategory = new ArrayList<AbuseRecord>();

            allRecordsOfIp.stream().forEach(record -> {
                if (record.getData().getAbuseCategories().iterator().next() == category) {
                    recordsByCategory.add(record);
                }
            });
            return recordsByCategory;
        } catch(Exception e) {
            throw e;
        }
    }
}
