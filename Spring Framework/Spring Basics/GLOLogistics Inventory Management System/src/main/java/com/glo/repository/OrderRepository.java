package com.glo.repository;

import com.glo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(String status);
    List<Order> findByCustomerId(Long custId);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = 'PENDING'")
    List<Order> findPendingOrders();
}