package com.maltego.api.service;

import com.maltego.api.entity.Geolocation;
import com.maltego.api.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GeolocationServiceImpl implements GeolocationService{

    @Autowired
    private GeolocationRepository geolocationRepository;

    @Transactional
    public String createGeolocation(Geolocation geolocation) {
        try {
            if (!geolocationRepository.existsByIpAddress(geolocation.getIpAddress())){
                geolocation.setId(null == geolocationRepository.findMaxId()? 0 : geolocationRepository.findMaxId() + 1);
                geolocationRepository.save(geolocation);
                return "Geolocation created successfully.";
            }else {
                return "IP Address already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Geolocation> readAll() {
        return geolocationRepository.findAll();
    }

    @Transactional
    public List<Geolocation> getGeolocation(String ipAddress) {

        return geolocationRepository.queryBy(ipAddress);
    }

}
