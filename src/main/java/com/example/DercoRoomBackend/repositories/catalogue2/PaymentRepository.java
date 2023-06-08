package com.example.DercoRoomBackend.repositories.catalogue2;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.catalogue2.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
