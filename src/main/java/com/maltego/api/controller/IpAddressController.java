package com.maltego.api.controller;

import com.maltego.api.entity.Data;
import com.maltego.api.entity.IpAddress;
import com.maltego.api.service.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IpAddressController {

    @Autowired
    private IpAddressService ipAddressService;

    @RequestMapping(value = "createIpAddress", method = RequestMethod.POST)
    public String createGeolocation(@RequestBody IpAddress ipAddress){
        return  this.ipAddressService.createIpAddress(ipAddress);
    }

    @RequestMapping(value = "readAll", method = RequestMethod.GET)
    public List<IpAddress> readAll(){
        return  this.ipAddressService.readAll();
    }

    @RequestMapping(value = "getGeolocation", method = RequestMethod.GET)
    public IpAddress getGeolocation(@RequestParam("ipAddress") String ipAddress){
        return  this.ipAddressService.getGeolocation(ipAddress);
    }

    @RequestMapping(value = "abuse", method = RequestMethod.POST)
    public Data createRecord(@RequestBody Data data){
        return this.ipAddressService.createRecord(data);
    }
}
