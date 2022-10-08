package com.maltego.api.repository;

import com.maltego.api.entity.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Integer> {

    boolean existsByIpAddress(String ipAddress);

    @Query("SELECT DISTINCT g FROM Geolocation g LEFT JOIN g.location WHERE g.location.locationId = g.id AND g.ipAddress = :ipAddress")
    List<Geolocation> queryBy(String ipAddress);

    @Query("select max(g.id) from Geolocation g")
    Integer findMaxId();
}
