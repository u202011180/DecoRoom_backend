package com.example.DercoRoomBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

import java.util.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_user;
    private String user_name;
    private String shippingmethod;
    private String adress_shipping;
    private String paymentmethod;
    private Integer quantityproducts;
    private Date orderdate;
    private Double totalpaid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    public Order(Long id_user, String user_name, String shippingmethod, String adress_shipping,
            String paymentmethod, Integer quantityproducts, Date orderdate, Double totalpaid, User user) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.shippingmethod = shippingmethod;
        this.adress_shipping = adress_shipping;
        this.paymentmethod = paymentmethod;
        this.quantityproducts = quantityproducts;
        this.orderdate = orderdate;
        this.totalpaid = totalpaid;
        this.user = user;
    }


}
