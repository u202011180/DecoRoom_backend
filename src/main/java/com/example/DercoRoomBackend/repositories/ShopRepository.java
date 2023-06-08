package com.example.DercoRoomBackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.DercoRoomBackend.models.*;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query(value = "SELECT s FROM Shop s WHERE s.id=?1", nativeQuery = true)
    Shop findByIdJPQL(String id);

    @Query(value = "SELECT s FROM Shop s WHERE s.user=?1", nativeQuery = true)
    Shop findByUserJPQL(User user);

    @Query(value = "SELECT u FROM User u WHERE u.shop=?1", nativeQuery = true)
    User findByShopJPQL(Shop shop);
}
