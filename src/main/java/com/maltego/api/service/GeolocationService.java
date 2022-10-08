package com.maltego.api.service;

import com.maltego.api.entity.Geolocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeolocationService {

    String createGeolocation(Geolocation geolocation);

    List<Geolocation> readAll();

    List<Geolocation> getGeolocation(String ipAddress);

    
}
