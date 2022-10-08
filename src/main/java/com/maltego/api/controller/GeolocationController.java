package com.maltego.api.controller;

import com.maltego.api.entity.Geolocation;
import com.maltego.api.service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GeolocationController {

    @Autowired
    private GeolocationService geolocationService;

    @RequestMapping(value = "createGeolocation", method = RequestMethod.POST)
    public String createGeolocation(@RequestBody Geolocation geolocation){
        return  this.geolocationService.createGeolocation(geolocation);
    }

    @RequestMapping(value = "readAll", method = RequestMethod.GET)
    public List<Geolocation> readAll(){
        return  this.geolocationService.readAll();
    }

    @RequestMapping(value = "getGeolocation", method = RequestMethod.GET)
    public List<Geolocation> getGeolocation(@RequestParam("ipAddress") String ipAddress){
        return  this.geolocationService.getGeolocation(ipAddress);
    }
}
