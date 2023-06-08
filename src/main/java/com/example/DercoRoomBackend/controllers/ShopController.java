package com.example.DercoRoomBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.DercoRoomBackend.repositories.CartProductRepository;
import com.example.DercoRoomBackend.repositories.ProductRepository;
import com.example.DercoRoomBackend.repositories.ShopRepository;
import com.example.DercoRoomBackend.repositories.UserRepository;
import com.example.DercoRoomBackend.exceptions.*;

import com.example.DercoRoomBackend.models.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> getAllShops(){
        List<Shop> shops = shopRepository.findAll();

        for(Shop shop: shops){
            shop.setUser(null);
            shop.setProductList(null);
        }

        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }
    @GetMapping("/shops_complete")
    public ResponseEntity<List<Shop>> getAllShopsData()
    {
        List<Shop> shops = shopRepository.findAll();
        for(Shop shop: shops)
        {
            shop.getUser().setShop(null);
            shop.getUser().setCart(null);

            for (Product product: shop.getProductList())
            {
                product.setShop(null);
                product.setCartProducts(null);
            }
        }

        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }
    @PostMapping("/shops/{id}")
    public ResponseEntity<Shop> createShop(@PathVariable("id") Long id, @RequestBody Shop shop){

        User userOwner = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));;
        Shop newShop = shopRepository.save(new Shop(
                shop.getIdUser(),
                shop.getName(),
                shop.getPhone(),
                shop.getAdress(),
                shop.getDescripción(),
                shop.getAmountProducts(),
                shop.getAceptación(),
                userOwner
        ));
        return new ResponseEntity<Shop>(newShop, HttpStatus.OK);
    }
    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable("id") Long id)
    {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        shop.setUser(null);
        shop.setProductList(null);
        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }
    @PutMapping("/shops/{id}")
    public  ResponseEntity<Shop> updateShopById(@PathVariable("id") Long id, @RequestBody Shop shop)
    {
        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;

        if(shop.getName() != null)
            foundShop.setName(shop.getName());
        if(shop.getPhone() != null)
            foundShop.setPhone(shop.getPhone());
        if(shop.getAdress() != null)
            foundShop.setAdress(shop.getAdress());
        if(shop.getDescripción() != null)
            foundShop.setDescripción(shop.getDescripción());
        if(shop.getAmountProducts() != null)
            foundShop.setAmountProducts(shop.getAmountProducts());
        if(shop.getAceptación() != null)
            foundShop.setAceptación(shop.getAceptación());

        foundShop.setProductList(null);
        foundShop.setUser(null);
        Shop updateShop = shopRepository.save(foundShop);

        return new ResponseEntity<Shop>(updateShop, HttpStatus.OK);
    }
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<HttpStatus> deleteShopByID(@PathVariable("id") Long id)
    {
        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        for(Product product: foundShop.getProductList())
        {
            for (CartProduct cartProduct: product.getCartProducts())
            {
                cartProductRepository.deleteById(cartProduct.getId());
            }
            productRepository.deleteById(product.getId());
        }

        shopRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
