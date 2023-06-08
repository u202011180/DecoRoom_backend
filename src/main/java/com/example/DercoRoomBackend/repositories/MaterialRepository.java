package com.example.DercoRoomBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.catalogue.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
