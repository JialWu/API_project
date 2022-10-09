package com.maltego.api.service;

import com.maltego.api.entity.Data;
import com.maltego.api.entity.IpAddress;
import com.maltego.api.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class IpAddressServiceImpl implements IpAddressService {

    @Autowired
    private IpAddressRepository ipAddressRepository;

    @Transactional
    public String createIpAddress(IpAddress ipAddress) {
        try {
            if (!ipAddressRepository.existsByIpAddress(ipAddress.getIpAddress())){
                ipAddress.setId(null == ipAddressRepository.findMaxId()? 0 : ipAddressRepository.findMaxId() + 1);
                ipAddressRepository.save(ipAddress);
                return "Geolocation created successfully.";
            }else {
                return "IP Address already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<IpAddress>  readAll() {
        return ipAddressRepository.findAll();
    }

    @Transactional
    public IpAddress getGeolocation(String ipAddress) {
        try {
            if(ipAddressRepository.existsByIpAddress(ipAddress)){
                return ipAddressRepository.queryBy(ipAddress);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    @Override
    public Data createRecord(Data data) {
        IpAddress ipAddress = ipAddressRepository.queryBy(data.getIpAddress());
        Set<Data> listData = ipAddress.getData();
        listData.add(data);
        ipAddress.setData(listData);
        ipAddressRepository.save(ipAddress);
        return data;
    }

    @Transactional
    public Set<Data> getAbuseRecords(String ipAddress) {
        return ipAddressRepository.findAbuseRecords(ipAddress);
    }

    @Override
    public List<Data> getAbuseRecordsByCategory(String ipAddress, Integer category) {
        Set<Data> allRecords = ipAddressRepository.findAbuseRecords(ipAddress);
        List<Data> recordsByCategory = new ArrayList<Data>();

        allRecords.stream().forEach(record -> {
            if (record.getAbuseCategories().iterator().next() == category) {
                recordsByCategory.add(record);
            }
        });
        return recordsByCategory;
    }

}
