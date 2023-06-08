package com.example.DercoRoomBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

import java.util.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idShop;
    private String name;
    private String shopname;
    private Date pubdate;
    private String condicion;
    private Integer quantity;
    private Double price;
    private String size;
    private String material;
    private String brand;
    private String type;
    private String season;
    private String year;
    private String pricetype;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToOne(mappedBy = "product")
    private ProductImage productlmage;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts;
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;
    
    public Product(Long idShop, String name, String shopname, Date pubdate, String condicion, Integer quantity,
            Double price, String size, String material, String brand, String type, String season,
            String year, String pricetype, Shop shop, ProductImage productlmage) {

        this.idShop = idShop;
        this.name = name;
        this.shopname = shopname;
        this.pubdate = pubdate;
        this.condicion = condicion;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.material = material;
        this.brand = brand;
        this.type = type;
        this.season = season;
        this.year = year;
        this.pricetype = pricetype;
        this.shop = shop;
        this.productlmage = productlmage;
    }



}
