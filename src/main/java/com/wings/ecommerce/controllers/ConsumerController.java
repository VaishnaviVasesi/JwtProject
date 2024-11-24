package com.wings.ecommerce.controllers;

import com.wings.ecommerce.models.*;
import com.wings.ecommerce.repo.CartProductRepo;
import com.wings.ecommerce.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartProductRepo cartProductRepo;
    @GetMapping ("/cart")
    public ResponseEntity<Object> getCart(){
        User result = getLoggedInUser();
        var cart = cartRepo.findByUserUsername(result.getUsername());
        if (cart.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cart.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/cart")
    public ResponseEntity<Object> postCart(@RequestBody Product product){
        User user = getLoggedInUser();
        Optional<Cart> cartOptional = cartRepo.findByUserUsername(user.getUsername());
        Cart cart = cartOptional.orElse(new Cart(product.getPrice(),user,new ArrayList<>()));

        CartProduct existingProduct = cart.getCartProducts().stream().
                filter(p -> p.getProduct().getProductId().equals(product.getProductId()))
                .findFirst().orElse(null);
        if(existingProduct != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CartProduct cartProduct  = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProductRepo.save(cartProduct);
        return  ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/cart")
    public ResponseEntity<Object> putCart(){
        return  null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCart(){
        return null;
    }

    private  User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}
