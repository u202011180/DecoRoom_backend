package com.example.DercoRoomBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
