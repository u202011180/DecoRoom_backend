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
public class OrderProductController {
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/orders_products/order_id/{order_id}/product_id/{product_id}")
    public ResponseEntity<OrderProduct> createOrderProduct(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @RequestBody OrderProduct orderProduct){

        Order foundOrder = orderRepository.findById(order_id)
                .orElseThrow(()->new ResourceNotFoundException("Order not found"));
        foundOrder.setUser(null);
        foundOrder.setOrderProducts(null);

        Product foundProduct = productRepository.findById(product_id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        foundProduct.setShop(null);
        foundProduct.setCartProducts(null);

        OrderProduct newOrderProduct = orderProductRepository.save(new OrderProduct(
                foundOrder.getId(), foundProduct.getId(), orderProduct.getId_shop(), orderProduct.getProduct(),
                orderProduct.getQuantity(), orderProduct.getTotalprice(), foundOrder, foundProduct
        ));
        newOrderProduct.setObj_Product(null);
        newOrderProduct.setOrder(null);
        return new ResponseEntity<OrderProduct>(newOrderProduct, HttpStatus.CREATED);
    }

    @GetMapping("/orders_products")
    public ResponseEntity<List<OrderProduct>> getAllOrdersProducts(){
        List<OrderProduct> orderProducts = orderProductRepository.findAll();

        for(OrderProduct orderProduct: orderProducts)
        {
            orderProduct.setObj_Product(null);
            orderProduct.setOrder(null);
        }

        return new ResponseEntity<List<OrderProduct>>(orderProducts, HttpStatus.OK);
    }
}
