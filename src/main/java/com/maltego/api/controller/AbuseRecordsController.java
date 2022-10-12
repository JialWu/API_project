package com.maltego.api.controller;

import com.maltego.api.entity.Data;
import com.maltego.api.service.AbuseRecordsService;
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
public class AbuseRecordsController {

    @Autowired
    private AbuseRecordsService abuseRecordsService;

    private final Bucket bucket;

    public AbuseRecordsController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @RequestMapping(value = "abuse", method = RequestMethod.POST)
    public Data createRecord(@RequestBody Data data) {
        return this.abuseRecordsService.createRecord(data);
    }

    @RequestMapping(value = "getAbuseRecords", method = RequestMethod.GET)
    public ResponseEntity<Set<Data>> getAbuseRecords(@RequestParam("ipAddress") String ipAddress){
        if(this.bucket.tryConsume(1)) {
            return (ResponseEntity.ok(this.abuseRecordsService.getAbuseRecords(ipAddress)));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @RequestMapping(value = "getAbuseRecordsByCategory", method = RequestMethod.GET)
    public List<Data> getAbuseRecordsByCategory(@RequestParam("ipAddress") String ipAddress, @RequestParam("category") Integer category){
        return this.abuseRecordsService.getAbuseRecordsByCategory(ipAddress, category);
    }
}
