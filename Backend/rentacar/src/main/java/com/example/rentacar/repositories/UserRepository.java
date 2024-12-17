package com.example.rentacar.repositories;

import com.example.rentacar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Kullanıcı veritabanı işlemleri için repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByNationalId(String nationalId); // TC kimlik numarasına göre kullanıcı bulma
}


