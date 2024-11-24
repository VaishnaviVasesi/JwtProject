package com.wings.ecommerce.controllers;

import com.wings.ecommerce.models.*;
import com.wings.ecommerce.repo.CartProductRepo;
import com.wings.ecommerce.repo.CartRepo;
import com.wings.ecommerce.util.UserUtil;
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
    @GetMapping ("/cart")
    public ResponseEntity<Object> getCart(){
        User result = UserUtil.getLoggedInUser();
        var cart = cartRepo.findByUserUsername(result.getUsername());
        if (cart.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cart.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("/cart")
    public ResponseEntity<Object> postCart(@RequestBody Product product){
        User user = UserUtil.getLoggedInUser();
        Optional<Cart> cartOptional = cartRepo.findByUserUsername(user.getUsername());
        Cart cart = cartOptional.orElse(new Cart(0.0,user,new ArrayList<>()));
        CartProduct existingProduct = getExistingProduct(product, cart);
        if(existingProduct != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CartProduct cartProduct  = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cart.getCartProducts().add(cartProduct);
        cart.updateTotalAmount(product.getPrice());
        cartRepo.save(cart);
        return  ResponseEntity.ok("Product added successfully");
    }


    @PutMapping("/cart")
    public ResponseEntity<Object> putCart(@RequestBody CartProduct cartProduct){
        User user = UserUtil.getLoggedInUser();
        Optional<Cart> cartOptional = cartRepo.findByUserUsername(user.getUsername());
        Cart cart = cartOptional.get();
        CartProduct existingProduct = getExistingProduct(cartProduct.getProduct(), cart);
        if(existingProduct != null) {
            if(cartProduct.getQuantity() > 0) {
                existingProduct.setQuantity(cartProduct.getQuantity() + existingProduct.getQuantity());
            }else {
                var remainingCartProducts =  cart.getCartProducts().stream()
                         .filter(cp -> !cp.getProduct().getProductId().
                                 equals(cartProduct.getProduct().getProductId())).toList();
                cart.setCartProducts(remainingCartProducts);
            }
        }
        cartRepo.save(cart);
        return ResponseEntity.ok("Cart updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCart(@RequestBody Product product){
        User user = UserUtil.getLoggedInUser();
        Optional<Cart> cartOptional = cartRepo.findByUserUsername(user.getUsername());
        Cart cart = cartOptional.get();

        var remainingCartProducts =  cart.getCartProducts().stream()
                .filter(cp -> !cp.getProduct().getProductId().
                        equals(product.getProductId())).toList();
        cart.setCartProducts(remainingCartProducts);
        cartRepo.save(cart);
        return ResponseEntity.ok("Product deleted successfully");
    }
    
    private static CartProduct getExistingProduct(Product product, Cart cart) {
        CartProduct existingProduct = cart.getCartProducts().stream().
                filter(p -> p.getProduct().getProductId().equals(product.getProductId()))
                .findFirst().orElse(null);
        return existingProduct;
    }
}
