package com.maltego.api.repository;

import com.maltego.api.entity.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Integer> {

    boolean existsByIpAddress(String ipAddress);

    @Query("select max(g.id) from Geolocation g")
    Integer findMaxId();

    @Query("SELECT g FROM Geolocation g INNER JOIN g.location WHERE g.location.locationId = g.id AND g.ipAddress = :ipAddress")
    Geolocation findGeolocation(String ipAddress);

    @Query("SELECT COUNT(g) = 1 FROM Geolocation g where g.ipAddress = :ipAddress")
    boolean findExistByIpAddress(String ipAddress);
}
