package com.example.rentacar.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Araba modeli, Rent A Car uygulamasında araç bilgilerini temsil eder.
 */
@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID otomatik artırılır
    private Long id;

    @Column(nullable = false) // Zorunlu alan
    private String brand; // Marka (ör. Porsche, Mercedes)

    @Column(nullable = false)
    private String model; // Model (ör. Taycan, E-Class)

    @Column(nullable = false)
    private String color; // Renk

    @Column(nullable = false)
    private boolean isAutomatic; // Otomatik mi manuel mi

    @Column(nullable = false)
    private int kmAge; // Kaç km'de

    @Column(nullable = false)
    private double dailyPrice; // Günlük fiyat

    @Column(nullable = true) // İsteğe bağlı alan
    private String imageUrl; // Araba görselinin URL'si
}
