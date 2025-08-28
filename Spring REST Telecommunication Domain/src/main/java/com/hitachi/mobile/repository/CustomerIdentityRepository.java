package com.hitachi.mobile.repository;

import com.hitachi.mobile.entity.CustomerIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentity, String> {
    Optional<CustomerIdentity> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);
}