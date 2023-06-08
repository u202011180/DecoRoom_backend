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
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private ProductImageRepository productlmageRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();

        for(Product product: products){
            product.setShop(null);
            product.setCartProducts(null);
            product.setProductlmage(null);
            product.setOrderProducts(null);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @PostMapping("/products/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable("id") Long id, @RequestBody Product product){

        Shop foundShop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));
        foundShop.setUser(null);
        foundShop.setProductList(null);
        Product newProduct = productRepository.save(new Product(
                foundShop.getId(),
                product.getName(),
                product.getShopname(),
                new Date(),
                product.getCondicion(),
                product.getQuantity(),
                product.getPrice(),
                product.getSize(),
                product.getMaterial(),
                product.getBrand(),
                product.getType(),
                product.getSeason(),
                product.getYear(),
                product.getPricetype(),
                foundShop,
                product.getProductlmage()
        ));
        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));;
        product.setShop(null);
        product.setCartProducts(null);
        product.setProductlmage(null);
        product.setOrderProducts(null);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    @GetMapping("products/shop_id/{id}")
    public ResponseEntity<List<Product>> getProductsByShop(@PathVariable("id") Long id)
    {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found"));;
        List<Product> products = productRepository.findByShop(shop);
        for(Product product: products)
        {
            product.setShop(null);
            product.setCartProducts(null);
            product.setProductlmage(null);
            product.setOrderProducts(null);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @PutMapping("/products/{id}")
    public  ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody Product product)
    {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));

        if(product.getName() != null)
            foundProduct.setName(product.getName());
        if(product.getShopname() != null)
            foundProduct.setShopname(product.getShopname());
        if(product.getCondicion() != null)
            foundProduct.setCondicion(product.getCondicion());
        if(product.getQuantity() != null)
            foundProduct.setQuantity(product.getQuantity());
        if(product.getPrice() != null)
            foundProduct.setPrice(product.getPrice());
        if(product.getSize() != null)
            foundProduct.setSize(product.getSize());
        if(product.getMaterial() != null)
            foundProduct.setMaterial(product.getMaterial());
        if(product.getBrand() != null)
            foundProduct.setBrand(product.getBrand());
        if(product.getType() != null)
            foundProduct.setType(product.getType());
        if(product.getSeason() != null)
            foundProduct.setSeason(product.getSeason());
        if(product.getYear() != null)
            foundProduct.setYear(product.getYear());
        if(product.getPricetype() != null)
            foundProduct.setPricetype(product.getPricetype());


        Product updateProduct = productRepository.save(foundProduct);
        updateProduct.setCartProducts(null);
        updateProduct.setShop(null);
        updateProduct.setProductlmage(null);
        updateProduct.setOrderProducts(null);

        return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Long id)
    {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));
        for(CartProduct cartProduct: foundProduct.getCartProducts())
        {
            cartProductRepository.deleteById(cartProduct.getId());
        }
        ProductImage productImage = productlmageRepository.findByProduct(foundProduct);
        productlmageRepository.deleteById(productImage.getId());
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
