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
import com.example.DercoRoomBackend.repositories.ProductRepository;
import com.example.DercoRoomBackend.repositories.ProductImageRepository;
import com.example.DercoRoomBackend.exceptions.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductImageController{
    @Autowired
    ProductImageRepository productlmageRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products_images")
    public ResponseEntity<List<ProductImage>> getAllProductsImages(){
        List<ProductImage> productImages = productlmageRepository.findAll();

        for(ProductImage productImage: productImages){
            productImage.setProduct(null);
        }
        return new ResponseEntity<List<ProductImage>>(productImages, HttpStatus.OK);
    }
    @PostMapping("/products_images/{id}")
    public ResponseEntity<ProductImage> createProductImage(@PathVariable("id") Long id, @RequestBody ProductImage image){

        Product foundProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        foundProduct.setCartProducts(null);
        foundProduct.setShop(null);
        foundProduct.setProductlmage(null);
        ProductImage newImage = productlmageRepository.save(new ProductImage(
                foundProduct.getId(), image.getImg(), foundProduct
        ));
        return new ResponseEntity<ProductImage>(newImage, HttpStatus.CREATED);
    }

    @GetMapping("/products_images/{id}")
    public ResponseEntity<ProductImage> getProductImageById(@PathVariable("id") Long id)
    {
        ProductImage productImage = productlmageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image not found"));;
        productImage.setProduct(null);
        return new ResponseEntity<ProductImage>(productImage, HttpStatus.OK);
    }

    @PutMapping("/products_images/{id}")
    public  ResponseEntity<ProductImage> updateProductImageById(@PathVariable("id") Long id, @RequestBody ProductImage image)
    {
        ProductImage founProductlmage = productlmageRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Image not found"));

        if(image.getId_product() != null)
            founProductlmage.setId_product(image.getId_product());
        if(image.getImg() != null)
            founProductlmage.setImg(image.getImg());

        ProductImage updateImage = productlmageRepository.save(founProductlmage);
        updateImage.setProduct(null);

        return new ResponseEntity<ProductImage>(updateImage, HttpStatus.OK);
    }
    @DeleteMapping("/products_images/{id}")
    public ResponseEntity<HttpStatus> deleteProductImageById(@PathVariable("id") Long id)
    {
        ProductImage foundImage = productlmageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Image not found"));
        foundImage.setProduct(null);
        productlmageRepository.deleteById(foundImage.getId_product());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}