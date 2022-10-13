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
        try {
            return geolocationRepository.findGeolocation(ipAddress);
        } catch (Exception e) {
            throw e;
        }
    }
}
