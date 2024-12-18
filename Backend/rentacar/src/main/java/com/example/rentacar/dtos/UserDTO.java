package com.example.rentacar.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Kullanıcı bilgilerini güvenli bir şekilde taşımak için DTO.
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String phoneNumber;
    private String address;
}
