package com.interShop.interShop.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "is_available")
    private boolean iaAvailable;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(Long id, String name, String description, double price, boolean iaAvailable, Category category) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.iaAvailable = iaAvailable;
        this.category = category;
    }

    public Product() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isIaAvailable() {
        return iaAvailable;
    }

    public void setIaAvailable(boolean iaAvailable) {
        this.iaAvailable = iaAvailable;
    }
}
