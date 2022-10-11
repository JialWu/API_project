package com.maltego.api.service;

import com.maltego.api.entity.Geolocation;
import com.maltego.api.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class GeolocationServiceImpl implements GeolocationService {

    @Autowired
    private GeolocationRepository geolocationRepository;

    @Transactional
    public String createIpAddress(Geolocation ipAddress) {
        try {
            if (!geolocationRepository.existsByIpAddress(ipAddress.getIpAddress())){
                ipAddress.setId(null == geolocationRepository.findMaxId()? 0 : geolocationRepository.findMaxId() + 1);
                geolocationRepository.save(ipAddress);
                return "Geolocation created successfully.";
            }else {
                return "IP Address already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Geolocation>  readAll() {
        return geolocationRepository.findAll();
    }

    @Override
    public Geolocation getGeolocation(String ipAddress) {
        return geolocationRepository.findGeolocation(ipAddress);
    }

    //@Override
    //public Data createRecord(Data data) {
    //    Geolocation ipAddress = ipAddressRepository.findIpAddress(data.getIpAddress());
    //    Set<Data> listData = ipAddress.getData();
    //    listData.add(data);
    //    ipAddress.setData(listData);
    //    ipAddressRepository.save(ipAddress);
    //    return data;
    //}

    //@Transactional
    //public Set<Data> getAbuseRecords(String ipAddress) {
    //    return ipAddressRepository.findAbuseRecords(ipAddress);
    //}

    //@Override
    //public List<Data> getAbuseRecordsByCategory(String ipAddress, Integer category) {
    //    Set<Data> allRecords = ipAddressRepository.findAbuseRecords(ipAddress);
    //    List<Data> recordsByCategory = new ArrayList<Data>();

    //    allRecords.stream().forEach(record -> {
    //        if (record.getAbuseCategories().iterator().next() == category) {
    //            recordsByCategory.add(record);
    //        }
    //    });
    //    return recordsByCategory;
    //}

}
