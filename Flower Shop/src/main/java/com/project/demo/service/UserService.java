package com.project.demo.service;

import com.project.demo.model.User;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(UUID userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setLastName(updatedUser.getLastName());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setPhoneNumber(updatedUser.getPhoneNumber());
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
