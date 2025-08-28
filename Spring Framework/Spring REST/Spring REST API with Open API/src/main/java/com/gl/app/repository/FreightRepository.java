package com.gl.app.repository;

import com.gl.app.entity.Freight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FreightRepository extends JpaRepository<Freight,Long> {
    List<Freight> findByCustomerId(long customerId);
    Boolean existsByCustomerId(long customerId);
}