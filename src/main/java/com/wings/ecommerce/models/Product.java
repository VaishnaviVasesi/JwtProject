package com.wings.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer productId;
    private String productName;
    private Double price;

    @ManyToOne()
    @JoinColumn(name="seller_id",referencedColumnName = "user_id",updatable=false)
    @JsonIgnore
    private User seller;

    @ManyToOne()
    @JoinColumn(name="category_id",referencedColumnName = "categoryId")
    private Category category;

    public Product() {
        super();
    }

    public Product(Integer productId, String productName, Double price, User seller, Category category) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.seller = seller;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
