package com.example.rentacar.services;

import com.example.rentacar.models.User;
import com.example.rentacar.repositories.UserRepository;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Getter
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection ile PasswordEncoder alıyoruz
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        // Şifreyi encode ederek kaydediyoruz
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public Optional<User> findByNationalId(String nationalId) {
        return userRepository.findByNationalId(nationalId); // TC kimlik no ile kullanıcı arama
    }
}
