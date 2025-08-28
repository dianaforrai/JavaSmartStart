package com.hitachi.mobile.repository;

import com.hitachi.mobile.entity.SimOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SimOffersRepository extends JpaRepository<SimOffers, Long> {
    List<SimOffers> findBySimId(Long simId);
}