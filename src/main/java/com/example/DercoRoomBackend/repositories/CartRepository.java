package com.example.DercoRoomBackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.*;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
