package com.project.demo.controller;

import com.project.demo.model.Flower;
import com.project.demo.service.FlowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/flowers")
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @PostMapping
    public ResponseEntity<Flower> addFlower(@RequestBody Flower flower) {
        Flower savedFlower = flowerService.addFlower(flower);
        return new ResponseEntity<>(savedFlower, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Flower>> getAllFlowers() {
        List<Flower> flowers = flowerService.getAllFlowers();
        return new ResponseEntity<>(flowers, HttpStatus.OK);
    }

    @GetMapping("/{flowerId}")
    public ResponseEntity<Flower> getFlowerById(@PathVariable UUID flowerId) {
        Optional<Flower> flower = flowerService.getFlowerById(flowerId);
        return flower.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{flowerId}/availability")
    public ResponseEntity<Flower> changeAvailability(@PathVariable UUID flowerId, @RequestParam boolean newAvailability) {
        Flower updatedFlower = flowerService.changeAvailability(flowerId, newAvailability);

        if (updatedFlower != null) {
            return new ResponseEntity<>(updatedFlower, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
