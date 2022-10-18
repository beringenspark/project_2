package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Integer deleteByProductOwnerUsernameAndId(String ownerUsername,Long id);
    public List<Product> findAllByProductOwnerUsername(String username);
}
