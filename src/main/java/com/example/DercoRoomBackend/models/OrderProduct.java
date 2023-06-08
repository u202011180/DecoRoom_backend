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
@Table(name = "orders_products")
@Data
@NoArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long id_order;
    private Long id_product;
    private Long id_shop;
    private String product;
    private Integer quantity;
    private Double totalprice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product obj_Product;

    public OrderProduct(Long id_order, Long id_product, Long id_shop, String product, Integer quantity,
            Double totalprice, Order order, Product obj_Product) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.id_shop = id_shop;
        this.product = product;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.order = order;
        this.obj_Product = obj_Product;
    }


}
