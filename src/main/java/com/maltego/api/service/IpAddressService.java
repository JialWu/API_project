package com.maltego.api.service;

import com.maltego.api.entity.Geolocation;
import com.maltego.api.entity.IpAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IpAddressService {

    String createIpAddress(IpAddress ipAddress);

    List<IpAddress> readAll();

    List<IpAddress> getGeolocation(String ipAddress);

    void createRecord();
}
