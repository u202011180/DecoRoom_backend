package com.example.DercoRoomBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.DercoRoomBackend.models.*;
import com.example.DercoRoomBackend.repositories.*;
import com.example.DercoRoomBackend.exceptions.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        for(Order order: orders){
            order.setOrderProducts(null);
            order.setUser(null);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id)
    {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order not found"));
        order.setUser(null);
        order.setOrderProducts(null);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PostMapping("/orders/user_id/{id}")
    public ResponseEntity<Order> createOrder(@PathVariable("id") Long id, @RequestBody Order order){

        User foundUser = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Order newOrder = orderRepository.save(new Order(
                foundUser.getId(), foundUser.getName(), order.getShippingmethod(),
                order.getAdress_shipping(),order.getPaymentmethod(), order.getQuantityproducts(),
                order.getOrderdate(), order.getTotalpaid(), foundUser
        ));
        newOrder.setUser(null);
        return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);
    }
}
