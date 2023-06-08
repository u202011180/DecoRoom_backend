package com.example.DercoRoomBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "carts_products")
@Data
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long product_id;
    private Long shopcart_id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    
    public CartProduct(Long product_id, Long shopcart_id, Integer quantity, Cart cart, Product product) {
        this.product_id = product_id;
        this.shopcart_id = shopcart_id;
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }

}
