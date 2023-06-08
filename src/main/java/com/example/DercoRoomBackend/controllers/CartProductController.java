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

import com.example.DercoRoomBackend.models.*;
import com.example.DercoRoomBackend.repositories.*;
import com.example.DercoRoomBackend.exceptions.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CartProductController {
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/carts_products")
    public ResponseEntity<List<CartProduct>> getAllCartsProducts(){
        List<CartProduct> cartProducts = cartProductRepository.findAll();

        for(CartProduct cartProduct: cartProducts)
        {
            cartProduct.setProduct(null);
            cartProduct.setCart(null);
        }

        return new ResponseEntity<List<CartProduct>>(cartProducts, HttpStatus.OK);
    }
    @GetMapping("/carts_products/{id}")
    public ResponseEntity<CartProduct> getCartProductById(@PathVariable("id") Long id)
    {
        CartProduct cartProduct = cartProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("CartProduct not found"));
        cartProduct.setProduct(null);
        cartProduct.setCart(null);
        return new ResponseEntity<CartProduct>(cartProduct, HttpStatus.OK);
    }
    @GetMapping("/carts_products/cart_id/{id}")
    public ResponseEntity<List<CartProduct>> getCartByUser(@PathVariable("id") Long id)
    {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        List<CartProduct> cartProducts = cartProductRepository.findByCart(cart);
        return new ResponseEntity<List<CartProduct>>(cartProducts, HttpStatus.OK);
    }
    @PostMapping("/carts_products/cart_id/{cart_id}/product_id/{product_id}")
    public ResponseEntity<CartProduct> createCart(@PathVariable("cart_id") Long cart_id,@PathVariable("product_id") Long product_id, @RequestBody CartProduct cartProduct){

        Cart foundCart = cartRepository.findById(cart_id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        foundCart.setCartProducts(null);
        foundCart.setUser(null);
        Product foundProduct = productRepository.findById(product_id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        foundProduct.setShop(null);
        foundProduct.setCartProducts(null);

        CartProduct newCartProduct = cartProductRepository.save(new CartProduct(
                foundProduct.getId(),
                foundCart.getId(),
                cartProduct.getQuantity(),
                foundCart,
                foundProduct
        ));
        return new ResponseEntity<CartProduct>(newCartProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/carts_products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Long id)
    {
        CartProduct foundCartProduct = cartProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("CartProduct not found"));

        cartProductRepository.deleteById(foundCartProduct.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/carts_products/{id}")
    public  ResponseEntity<CartProduct> updateCartById(@PathVariable("id") Long id, @RequestBody CartProduct cartProduct)
    {
        CartProduct foundCartProduct = cartProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("CartProduct not found"));

        if(cartProduct.getQuantity() != null)
             foundCartProduct.setQuantity(cartProduct.getQuantity());


        CartProduct updateCartProduct = cartProductRepository.save(foundCartProduct);
        updateCartProduct.setProduct(null);
        updateCartProduct.setCart(null);

        return new ResponseEntity<CartProduct>(updateCartProduct, HttpStatus.OK);
    }
}
