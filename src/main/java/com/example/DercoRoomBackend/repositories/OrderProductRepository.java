package com.example.DercoRoomBackend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
