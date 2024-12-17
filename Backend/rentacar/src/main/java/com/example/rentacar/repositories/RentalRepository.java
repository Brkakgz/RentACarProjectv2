package com.example.rentacar.repositories;

import com.example.rentacar.models.Rental;
import com.example.rentacar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RentalRepository, kiralama verileriyle çalışmak için kullanılır.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUser(User user); // Kullanıcıya ait kiralamaları getir
}
