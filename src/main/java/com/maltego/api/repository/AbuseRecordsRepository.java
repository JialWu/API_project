package com.maltego.api.repository;

import com.maltego.api.entity.AbuseRecord;
import com.maltego.api.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AbuseRecordsRepository extends JpaRepository<AbuseRecord, Integer> {

    //@Query("select max(d.id) from Data d")
    //Integer findMaxId();

    @Query("SELECT r FROM AbuseRecord r WHERE r.data.ipAddress = :ipAddress")
    Set<AbuseRecord> findAbuseRecords(String ipAddress);
}
