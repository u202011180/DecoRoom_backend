package com.example.DercoRoomBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.*;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByShop(Shop shop);
    List<Product> findByIdShop(Long id);
}
