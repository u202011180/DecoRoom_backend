package com.example.DercoRoomBackend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_user;
    private Double total_purchase;
    private Integer quantity_products;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts;

    public Cart(Long id_user, Double total_purchase, Integer quantity_products, User user) {
        this.id_user = id_user;
        this.total_purchase = total_purchase;
        this.quantity_products = quantity_products;
        this.user = user;
    }

}
