package com.example.rentacar.controllers;

import com.example.rentacar.dtos.UserDTO;
import com.example.rentacar.models.User;
import com.example.rentacar.security.JwtUtils;
import com.example.rentacar.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthController, kullanıcı kayıt ve giriş işlemlerini yönetir.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder'ı ekledik

    public AuthController(UserService userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder; // Constructor injection
    }

    /**
     * Kullanıcı giriş işlemi
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        return userService.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword())) // Şifre doğrulaması
                .map(user -> {
                    String token = jwtUtils.generateToken(username);
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.badRequest().body(Map.of("error", "Geçersiz kullanıcı adı veya şifre")));
    }

    /**
     * Yeni kullanıcı kaydı
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        // Kullanıcı adı kontrolü
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Bu kullanıcı adı zaten alınmış.");
        }

        // TC Kimlik Numarası kontrolü
        if (userService.findByNationalId(user.getNationalId()).isPresent()) {
            return ResponseEntity.badRequest().body("Bu TC kimlik numarası zaten kayıtlı.");
        }

        // Kullanıcıyı kaydet
        user.setRole("USER"); // Varsayılan rol USER
        userService.saveUser(user);
        return ResponseEntity.ok("Kayıt başarılı.");
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Çıkış yapıldı. Lütfen token'ınızı silin.");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı."));

        UserDTO userDTO = new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getNationalId(),
                user.getPhoneNumber(),
                user.getAddress()
        );
        return ResponseEntity.ok(userDTO);
    }

}
