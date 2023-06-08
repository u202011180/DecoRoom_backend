package com.example.DercoRoomBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.*;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findByCart(Cart cart);
}
