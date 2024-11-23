package com.wings.ecommerce.controllers;

import com.wings.ecommerce.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/seller")
public class SellerController {
    @PostMapping("/product")
    public ResponseEntity<Object> postProduct(){
        return null;
    }
    @GetMapping("/product")
    public ResponseEntity<Object> getAllProducts(){
        return null;
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<Object> getProduct(){
        return null;
    }
    @PutMapping("/product")
    public ResponseEntity<Object> putProduct(){
        return null;
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> deleteProduct(){
        return null;
    }
}
