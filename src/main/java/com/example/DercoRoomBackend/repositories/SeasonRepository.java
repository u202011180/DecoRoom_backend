package com.example.DercoRoomBackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DercoRoomBackend.models.catalogue.Season;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}

