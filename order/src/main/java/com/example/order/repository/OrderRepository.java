package com.example.order.repository;

import com.example.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByOrderUsername(String username);
    Order findByOrderUsernameAndId(String orderUsername,Long id);
    Integer deleteByOrderUsernameAndId(String orderUsername, Long id);
}
