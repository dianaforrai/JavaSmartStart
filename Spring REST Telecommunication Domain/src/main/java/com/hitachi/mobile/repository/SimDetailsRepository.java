package com.hitachi.mobile.repository;

import com.hitachi.mobile.entity.SimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SimDetailsRepository extends JpaRepository<SimDetails, Long> {
    Optional<SimDetails> findByServiceNumber(String serviceNumber);
    boolean existsByServiceNumber(String serviceNumber);
}
