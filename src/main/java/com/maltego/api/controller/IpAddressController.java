package com.maltego.api.controller;

import com.maltego.api.entity.Data;
import com.maltego.api.entity.IpAddress;
import com.maltego.api.service.IpAddressService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@RestController
public class IpAddressController {

    @Autowired
    private IpAddressService ipAddressService;

    private final Bucket bucket;

    public IpAddressController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

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

    @RequestMapping(value = "getAbuseRecords", method = RequestMethod.GET)
    public ResponseEntity<Set<Data>> getAbuseRecords(@RequestParam("ipAddress") String ipAddress){
        if(this.bucket.tryConsume(1)) {
            return ResponseEntity.ok(this.ipAddressService.getAbuseRecords(ipAddress));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

    }

    @RequestMapping(value = "getAbuseRecordsByCategory", method = RequestMethod.GET)
    public List<Data> getAbuseRecordsByCategory(@RequestParam("ipAddress") String ipAddress, @RequestParam("category") Integer category){
        return this.ipAddressService.getAbuseRecordsByCategory(ipAddress, category);
    }

}
