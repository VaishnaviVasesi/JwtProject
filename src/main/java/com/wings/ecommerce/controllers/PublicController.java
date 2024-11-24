package com.wings.ecommerce.controllers;

import com.wings.ecommerce.models.Product;
import com.wings.ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private ProductRepo productRepo;
    @GetMapping("/product/search")
    public ResponseEntity<?> getProducts(@RequestParam String keyword){
        List<Product> list = productRepo.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(keyword,keyword);
        if(CollectionUtils.isEmpty(list)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return null;
    }
}
