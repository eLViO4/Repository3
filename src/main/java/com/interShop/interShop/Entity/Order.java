package com.interShop.interShop.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private double price;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    /*@Column(name = "status")
    private String status;*/


    public Order() {
    }

    public Order(Long id, User user, double price, LocalDateTime orderDate) {
        this.id = id;
        this.user = user;
        this.price = price;
        this.orderDate = orderDate;
    }

    /*public Order(Long id, User user, double price, LocalDateTime orderDate, String status) {
        this.id = id;
        this.user = user;
        this.price = price;
        this.orderDate = orderDate;
        this.status = status;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /*public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/
}

