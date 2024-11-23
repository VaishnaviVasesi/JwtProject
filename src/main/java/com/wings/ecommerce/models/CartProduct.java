package com.wings.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id","product_id"}))
@Entity
public class CartProduct {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer cpId;

    @ManyToOne()
    @JoinColumn(name="cart_id",referencedColumnName = "cartId")
    @JsonIgnore
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name="productt_id",referencedColumnName = "productId")
    private Product product;
    private  Integer quantity=1;

    public CartProduct() {
        super();
    }

    public CartProduct(Integer cpId, Cart cart, Product product, Integer quantity) {
        this.cpId = cpId;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
