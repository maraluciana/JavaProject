package com.project.demo.controller;

import com.project.demo.model.Flower;
import com.project.demo.model.User;
import com.project.demo.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @PostMapping("/add")
    public ResponseEntity<Flower> addFlower(@RequestBody Flower flower) {
        Flower newFlower = flowerService.addFlower(flower);
        return ResponseEntity.ok(newFlower);
    }

    @GetMapping()
    public List<Flower> getAllFlowers() {
        return flowerService.getAllFlowers();
    }

    @GetMapping("/{flowerId}")
    public ResponseEntity<Flower> getFlowerById(@PathVariable UUID flowerId) {
        Optional<Flower> flower = flowerService.getFlowerById(flowerId);
        return flower.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{flowerId}")
    public ResponseEntity<Flower> updateFlower(@PathVariable UUID flowerId, @RequestBody Flower updatedFlower) {
        Flower updated = flowerService.updateFlower(flowerId, updatedFlower);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-availability")
    public List<Flower> getFlowersByAvailability(@RequestBody String availability) {
        return flowerService.getFlowersByAvailability(availability);
    }

    @GetMapping("/by-price-range")
    public List<Flower> getFlowersInPriceRange(@RequestBody Map<String, String> requestBody) {
        String minPrice = requestBody.get("minPrice");
        String maxPrice = requestBody.get("maxPrice");

        return flowerService.getFlowersInPriceRange(Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
    }

    @DeleteMapping("/{flowerId}")
    public void deleteFlower(@PathVariable UUID flowerId) {
        flowerService.deleteFlower(flowerId);
    }
}
