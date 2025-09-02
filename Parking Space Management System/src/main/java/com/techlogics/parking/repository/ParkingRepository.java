package com.techlogics.parking.repository;

import com.techlogics.parking.entity.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {

    List<ParkingEntity> findByParkingAreaIdOrderByTimestampDesc(String parkingAreaId);

    @Query("SELECT p FROM ParkingEntity p WHERE p.timestamp BETWEEN :startTime AND :endTime ORDER BY p.timestamp DESC")
    List<ParkingEntity> findByTimestampBetween(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT p FROM ParkingEntity p WHERE p.availableSpaces < :threshold ORDER BY p.timestamp DESC")
    List<ParkingEntity> findLowAvailabilityRecords(@Param("threshold") Integer threshold);

    @Query("SELECT DISTINCT p.parkingAreaId FROM ParkingEntity p")
    List<String> findAllParkingAreaIds();
}