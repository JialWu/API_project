package com.maltego.api.controller;

import com.maltego.api.entity.AbuseRecord;
import com.maltego.api.entity.Data;
import com.maltego.api.repository.GeolocationRepository;
import com.maltego.api.service.AbuseRecordsService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
public class AbuseRecordsController {

    @Autowired
    private AbuseRecordsService abuseRecordsService;

    @Autowired
    private GeolocationRepository geolocationRepository;

    private final Bucket bucket;

    private LocalDateTime localDateTime= LocalDateTime.now();

    public AbuseRecordsController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @RequestMapping(value = "abuse", method = RequestMethod.POST)
    public ResponseEntity<AbuseRecord> createRecord1(@RequestParam("ipAddress") String ipAddress, @RequestParam("abuseCategories") List<Integer> abuseCategories) {
        if (geolocationRepository.findExistByIpAddress(ipAddress)) {
            for (Integer category : abuseCategories) {
                Data data = new Data(ipAddress, List.of(category), this.localDateTime);
                AbuseRecord a = new AbuseRecord(data);
                this.abuseRecordsService.createRecord(a);
            }
            Data d = new Data(ipAddress, abuseCategories, this.localDateTime);
            AbuseRecord record = new AbuseRecord(d);

            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "getAbuseRecords", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Set<AbuseRecord>> getAbuseRecordsJson(@RequestParam("ipAddress") String ipAddress){
        if (this.bucket.tryConsume(1)) {
            if (geolocationRepository.findExistByIpAddress(ipAddress)) {
                return (ResponseEntity.ok(this.abuseRecordsService.getAbuseRecords(ipAddress)));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @RequestMapping(value = "getAbuseRecordsByCategory", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AbuseRecord>> getAbuseRecordsByCategory(@RequestParam("ipAddress") String ipAddress, @RequestParam("category") Integer category){
        if (this.bucket.tryConsume(1)) {
            if (geolocationRepository.findExistByIpAddress(ipAddress)) {
                return (ResponseEntity.ok(this.abuseRecordsService.getAbuseRecordsByCategory(ipAddress, category)));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
