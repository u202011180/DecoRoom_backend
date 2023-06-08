package com.example.DercoRoomBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.*;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    ProductImage findByProduct(Product product);
}
