package com.example.rentacar.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Kiralama işlemlerini temsil eden model.
 */
@Entity
@Table(name = "rentals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // Kiralanan araba

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Kiralayan kullanıcı

    @Column(nullable = false)
    private LocalDate startDate; // Kiralama başlangıç tarihi

    @Column(nullable = false)
    private LocalDate endDate; // Kiralama bitiş tarihi

    @Column(nullable = false)
    private double totalPrice; // Toplam ücret

    @Column(nullable = false)
    private boolean isCompleted; // Kiralama tamamlandı mı?
}
