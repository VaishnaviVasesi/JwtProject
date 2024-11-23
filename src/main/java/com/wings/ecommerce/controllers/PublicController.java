package com.wings.ecommerce.controllers;

import com.wings.ecommerce.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/product/search")
    public List<Product> getProducts(){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return null;
    }
}
