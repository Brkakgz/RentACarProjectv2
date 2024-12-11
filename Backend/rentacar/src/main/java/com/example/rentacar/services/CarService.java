package com.example.rentacar.services;

import com.example.rentacar.models.Car;
import com.example.rentacar.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Araba verileriyle ilgili iş mantığını içerir.
 */
@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Tüm arabaları getir.
     */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    /**
     * ID'ye göre araba getir.
     */
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    /**
     * Yeni araba kaydet.
     */
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    /**
     * Araba sil.
     */
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}