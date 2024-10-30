package com.interShop.interShop.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public OrderItems() {
    }

    public OrderItems(Long id, int quantity, Products product, Orders order) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
