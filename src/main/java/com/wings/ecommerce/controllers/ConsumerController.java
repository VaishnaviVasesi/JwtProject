package com.wings.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {
    @GetMapping ("/cart")
    public ResponseEntity<Object> getCart(){
        return null;
    }

    @PostMapping("/cart")
    public ResponseEntity<Object> postCart(){
        return null;
    }

    @PutMapping("/cart")
    public ResponseEntity<Object> putCart(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCart(){
        return null;
    }

}
