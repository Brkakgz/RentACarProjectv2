package com.example.rentacar.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcı bilgilerini içeren model.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Kullanıcı adı boş olamaz.")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Şifre boş olamaz.")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Ad boş olamaz.")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Soyad boş olamaz.")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Size(min = 11, max = 11, message = "TC kimlik numarası 11 haneli olmalıdır.")
    @Pattern(regexp = "\\d+", message = "TC kimlik numarası sadece rakamlardan oluşmalıdır.")
    private String nationalId; // TC kimlik numarası

    @Column(nullable = false)
    @Pattern(regexp = "\\+?\\d{10,13}", message = "Geçerli bir telefon numarası girin.")
    private String phoneNumber; // Telefon numarası

    @Column(nullable = false)
    @NotBlank(message = "Adres boş olamaz.")
    private String address; // Kullanıcı adresi

    @Column(nullable = false)
    private String role; // Rol (USER veya ADMIN)
}
