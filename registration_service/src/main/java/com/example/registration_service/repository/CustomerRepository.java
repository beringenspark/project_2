package com.example.registration_service.repository;

import com.example.registration_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    public boolean existsByUsername(String username);
}
