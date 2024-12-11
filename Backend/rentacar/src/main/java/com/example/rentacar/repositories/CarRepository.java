package com.example.rentacar.repositories;

import com.example.rentacar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Araba verileriyle çalışmak için gerekli veritabanı işlemlerini sağlar.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Özel sorgular gerekirse burada tanımlanabilir (JPQL)
}