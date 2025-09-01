package com.hitachi.mobile.repository;

import com.hitachi.mobile.entity.SimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SimDetailsRepository extends JpaRepository<SimDetails, Long> {
    Optional<SimDetails> findBySimNumber(String simNumber);
    Optional<SimDetails> findByServiceNumber(String serviceNumber);
    List<SimDetails> findByCustomerId(Long customerId);
    boolean existsBySimNumber(String simNumber);
    boolean existsByServiceNumber(String serviceNumber);
}