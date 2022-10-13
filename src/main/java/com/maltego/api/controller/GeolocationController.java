package com.maltego.api.controller;

import com.maltego.api.entity.Geolocation;
import com.maltego.api.repository.GeolocationRepository;
import com.maltego.api.service.GeolocationService;
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

@RestController
public class GeolocationController {

    @Autowired
    private GeolocationService geolocationService;

    @Autowired
    private GeolocationRepository geolocationRepository;

    private final Bucket bucket;

    public GeolocationController() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/register")
    public ModelAndView showForm(Model model) {
        Geolocation ipAddress = new Geolocation();
        model.addAttribute("ipAddress", ipAddress);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register_form.html");

        return modelAndView;
    }
    @RequestMapping(value = "createIpAddress", method = RequestMethod.POST)
    public String createGeolocation(@RequestBody Geolocation ipAddress){
        return  this.geolocationService.createIpAddress(ipAddress);
    }

    @RequestMapping(value = "readAll", method = RequestMethod.GET)
    public List<Geolocation> readAll(){
        return  this.geolocationService.readAll();
    }

    @RequestMapping(value = "getGeolocation", method = RequestMethod.GET)
    public ResponseEntity<Geolocation> getGeolocation(@RequestParam("ipAddress") String ipAddress){
        if (geolocationRepository.findExistByIpAddress(ipAddress)) {
            return ResponseEntity.ok(this.geolocationService.getGeolocation(ipAddress));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
