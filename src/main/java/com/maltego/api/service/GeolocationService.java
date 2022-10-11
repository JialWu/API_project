package com.maltego.api.service;

import com.maltego.api.entity.Geolocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeolocationService {

    String createIpAddress(Geolocation geolocation);

    List<Geolocation> readAll();

    Geolocation getGeolocation(String ipAddress);
}