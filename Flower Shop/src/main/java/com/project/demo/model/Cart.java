package com.project.demo.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderDate DESC")
    private List<Order> orders;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Column(name = "is_cart_open", nullable = false)
    private boolean isCartOpen;

    public Cart() {
    }

    public Cart(User user, List<CartItem> cartItems, int totalPrice, boolean isCartOpen) {
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.isCartOpen = isCartOpen;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isCartOpen() {
        return isCartOpen;
    }

    public void setCartOpen(boolean cartOpen) {
        isCartOpen = cartOpen;
    }
}
