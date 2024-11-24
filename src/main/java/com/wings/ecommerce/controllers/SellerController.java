package com.wings.ecommerce.controllers;

import com.wings.ecommerce.models.Product;
import com.wings.ecommerce.models.User;
import com.wings.ecommerce.repo.ProductRepo;
import com.wings.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/seller")
public class SellerController {
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/product")
    public ResponseEntity<Object> postProduct(@RequestBody Product product){
        User user = UserUtil.getLoggedInUser();
        product.setSeller(user);
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/product")
    public ResponseEntity<Object> getAllProducts(){
        var products = productRepo.findBySellerUserId(UserUtil.getLoggedInUser().getUserId());
        return ResponseEntity.ok(products);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable Integer productId ){
        var products = productRepo.findBySellerUserIdAndProductId(UserUtil.getLoggedInUser().getUserId(), productId);
        return ResponseEntity.ok(products);
    }
    @PutMapping("/product")
    public ResponseEntity<Object> putProduct(@RequestBody Product product){
        User user = UserUtil.getLoggedInUser();
        product.setSeller(user);
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
        User user = UserUtil.getLoggedInUser();
        var product = productRepo.findBySellerUserIdAndProductId(user.getUserId(),productId);
        if(product.isPresent()) {
            productRepo.delete(product.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
