package com.project.demo.service;

import com.project.demo.model.Cart;
import com.project.demo.model.CartItem;
import com.project.demo.model.User;
import com.project.demo.repository.CartRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private CartRepository cartRepository;
    private UserRepository userRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Optional<Cart> createCart(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.map(user -> {
            // Close any existing open cart for the user
            Optional<Cart> openCart = cartRepository.findFirstByUserAndIsCartOpen(user, true);
            openCart.ifPresent(cart -> {
                cart.setCartOpen(false);
                cartRepository.save(cart);
            });

            // Create a new open cart for the user
            Cart newCart = new Cart(user, true);
            return Optional.of(cartRepository.save(newCart));
        }).orElse(Optional.empty());
    }

    public Cart addCartItem(UUID cartId, CartItem cartItem) {
        Optional<Cart> openCart = cartRepository.findById(cartId);
        return openCart.map(cart -> {
            if (cart.isCartOpen()) {
                cart.addCartItem(cartItem);
                return cartRepository.save(cart);
            } else {
                return null;
            }
        }).orElse(null);
    }

    public void closeCart(UUID cartId) {
        Optional<Cart> openCart = cartRepository.findById(cartId);
        openCart.ifPresent(cart -> {
            cart.setCartOpen(false);
            cartRepository.save(cart);
        });
    }
}
