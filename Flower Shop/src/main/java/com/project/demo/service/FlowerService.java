package com.project.demo.service;

import com.project.demo.model.Flower;
import com.project.demo.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    public Flower addFlower(Flower flower) {
        return flowerRepository.save(flower);
    }

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public Optional<Flower> getFlowerById(UUID flowerId) {
        return flowerRepository.findById(flowerId);
    }

    public Flower updateFlower(UUID flowerId, Flower updatedFlower) {
        Optional<Flower> existingFlower = flowerRepository.findById(flowerId);
        if (existingFlower.isPresent()) {
            Flower flowerToUpdate = existingFlower.get();
            flowerToUpdate.setName(updatedFlower.getName());
            flowerToUpdate.setDescription(updatedFlower.getDescription());
            flowerToUpdate.setPrice(updatedFlower.getPrice());
            flowerToUpdate.setImageUrl(updatedFlower.getImageUrl());
            flowerToUpdate.setAvailability(updatedFlower.getAvailability());
            return flowerRepository.save(flowerToUpdate);
        }
        return null;
    }

    public List<Flower> getFlowersByAvailability(String availability) {
        return flowerRepository.findByAvailability(availability);
    }

    public List<Flower> getFlowersInPriceRange(int minPrice, int maxPrice) {
        return flowerRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public void deleteFlower(UUID flowerId) {
        flowerRepository.deleteById(flowerId);
    }
}
