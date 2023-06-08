package com.example.DercoRoomBackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.catalogue.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
