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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.util.Arrays;
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

    @GetMapping("/register")
    public ModelAndView showForm(Model model) {
        IpAddress ipAddress = new IpAddress();
        model.addAttribute("ipAddress", ipAddress);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register_form.html");

        return modelAndView;
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
    public List<Object[]> getGeolocation(@RequestParam("ipAddress") String ipAddress){
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
