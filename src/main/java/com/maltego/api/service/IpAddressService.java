package com.maltego.api.service;

import com.maltego.api.entity.Data;
import com.maltego.api.entity.IpAddress;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IpAddressService {

    String createIpAddress(IpAddress ipAddress);

    List<IpAddress> readAll();

    IpAddress getGeolocation(String ipAddress);

    Data createRecord(Data data);

    Set<Data> getAbuseRecords(String ipAddress);

    List<Data> getAbuseRecordsByCategory(String ipAddress, Integer category);
}