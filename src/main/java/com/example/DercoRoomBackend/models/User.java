package com.example.DercoRoomBackend.models;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String dni;
    private String phone;
    private String adress;

    @OneToOne(mappedBy = "user")
    private Shop shop;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> OrderList;

    public User(String name, String lastname, String email, String password, String dni, String phone,
            String adress) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.phone = phone;
        this.adress = adress;
    }

}
