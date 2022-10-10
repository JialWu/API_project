package com.maltego.api.repository;

import com.maltego.api.entity.Data;
import com.maltego.api.entity.IpAddress;
import com.maltego.api.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Integer> {

    boolean existsByIpAddress(String ipAddress);

    @Query("select max(ip.id) from IpAddress ip")
    Integer findMaxId();

    @Query("SELECT ip FROM IpAddress ip INNER JOIN ip.location WHERE ip.location.locationId = ip.id AND ip.ipAddress = :ipAddress")
    IpAddress findIpAddress(String ipAddress);

    @Query("SELECT ip.id, ip.ipAddress, ip.location FROM IpAddress ip INNER JOIN ip.location WHERE ip.location.locationId = ip.id AND ip.ipAddress = :ipAddress")
    List<Object[]> findGeolocation(String ipAddress);

    @Query("SELECT DISTINCT ip.data FROM IpAddress ip JOIN ip.data d WHERE d.ipAddress = :ipAddress")
    Set<Data> findAbuseRecords(String ipAddress);
}
