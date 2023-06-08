package com.example.DercoRoomBackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.catalogue.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {
}
