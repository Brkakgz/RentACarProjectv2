package com.example.rentacar.controllers;

import com.example.rentacar.models.Car;
import com.example.rentacar.models.Rental;
import com.example.rentacar.models.User;
import com.example.rentacar.services.CarService;
import com.example.rentacar.services.RentalService;
import com.example.rentacar.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RentalController, kiralama işlemlerini içerir.
 */
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final CarService carService;
    private final UserService userService;

    public RentalController(RentalService rentalService, CarService carService, UserService userService) {
        this.rentalService = rentalService;
        this.carService = carService;
        this.userService = userService;
    }

    /**
     * Kullanıcının geçmiş kiralamalarını getirir.
     */
    @GetMapping("/history")
    public ResponseEntity<List<Rental>> getUserRentalHistory() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı."));

        return ResponseEntity.ok(rentalService.getUserRentals(user));
    }

    /**
     * Yeni bir kiralama oluştur.
     */
    @PostMapping("/{carId}")
    public ResponseEntity<Rental> createRental(@PathVariable Long carId, @RequestBody Rental rental) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı."));
        Car car = carService.getCarById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Araba bulunamadı."));

        Rental newRental = rentalService.createRental(user, car, rental);
        return ResponseEntity.ok(newRental);
    }
}
