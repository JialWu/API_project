package com.maltego.api.service;

import com.maltego.api.entity.Geolocation;
import com.maltego.api.entity.IpAddress;
import com.maltego.api.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IpAddressServiceImpl implements IpAddressService {

    @Autowired
    private IpAddressRepository ipAddressRepository;

    @Transactional
    public String createIpAddress(IpAddress ipAddress) {
        try {
            if (!ipAddressRepository.existsByIpAddress(ipAddress.getIpAddress())){
                ipAddress.setId(null == ipAddressRepository.findMaxId()? 0 : ipAddressRepository.findMaxId() + 1);
                ipAddressRepository.save(ipAddress);
                return "Geolocation created successfully.";
            }else {
                return "IP Address already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<IpAddress>  readAll() {
        return ipAddressRepository.findAll();
    }

    @Transactional
    public List<IpAddress> getGeolocation(String ipAddress) {

        return ipAddressRepository.queryBy(ipAddress);
    }

    @Override
    public void createRecord() {

    }

}
