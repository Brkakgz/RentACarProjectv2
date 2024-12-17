package com.example.rentacar.controllers;

import com.example.rentacar.models.Car;
import com.example.rentacar.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdminController, yönetici işlemlerini içerir.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final CarService carService;

    public AdminController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Yeni bir araba ekle
     */
    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.saveCar(car));
    }

    /**
     * Mevcut bir arabayı güncelle
     */
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.getCarById(id)
                .map(existingCar -> {
                    car.setId(id); // ID'yi koruyoruz
                    return ResponseEntity.ok(carService.saveCar(car));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Belirli bir arabayı sil
     */
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Tüm arabaları listele
     */
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }
}
