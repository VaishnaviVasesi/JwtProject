package com.wings.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer cartId;
    private Double totalAmount;

    @OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private User user;

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts;

    public Cart(){

        super();
    }

    public Cart(Double totalAmount, User user, List<CartProduct> cartProducts) {
        super();
        this.totalAmount = totalAmount;
        this.user = user;
        this.cartProducts = cartProducts;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalAmount=" + totalAmount +
                ", user=" + user +
                ", cartProducts=" + cartProducts +
                '}';
    }

    public void updateTotalAmount(Double price){
        this.totalAmount+=price;
    }
}
