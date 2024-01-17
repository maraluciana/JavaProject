package com.project.demo.service;

import com.project.demo.model.Category;
import com.project.demo.model.Flower;
import com.project.demo.repository.CategoryRepository;
import com.project.demo.repository.FlowerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlowerService {

    private FlowerRepository flowerRepository;
    private CategoryRepository categoryRepository;

    public FlowerService(FlowerRepository flowerRepository, CategoryRepository categoryRepository) {
        this.flowerRepository = flowerRepository;
        this.categoryRepository = categoryRepository;
    }

    public Flower addFlower(Flower flower) {
        Optional<Category> category = categoryRepository.findById(flower.getCategory().getId());

        if (category.isPresent()) {
            flower.setCategory(category.get());
            return flowerRepository.save(flower);
        } else {
            throw null;
        }
    }


    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public Optional<Flower> getFlowerById(UUID flowerId) {
        return flowerRepository.findById(flowerId);
    }

    public Flower changeAvailability(UUID flowerId, boolean newAvailability) {
        Optional<Flower> optionalFlower = flowerRepository.findById(flowerId);

        if (optionalFlower.isPresent()) {
            Flower flower = optionalFlower.get();
            flower.setAvailability(newAvailability);
            return flowerRepository.save(flower);
        }

        return null;
    }
}
