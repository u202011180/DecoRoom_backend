package com.example.DercoRoomBackend.models;

import java.util.List;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "shops")
@Data
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;
    private String name;
    private String phone;
    private String adress;
    private String descripción;
    private Integer amountProducts;
    private String aceptación;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shop")
    private List<Product> productList;

    public Shop(Long idUser, String name, String phone, String adress, String descripción,
            Integer amountProducts, String aceptación, User user) {
        this.idUser = idUser;
        this.name = name;
        this.phone = phone;
        this.adress = adress;
        this.descripción = descripción;
        this.amountProducts = amountProducts;
        this.aceptación = aceptación;
        this.user = user;
    }



}
