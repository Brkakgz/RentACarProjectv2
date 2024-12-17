package com.example.rentacar;

import com.example.rentacar.models.User;
import com.example.rentacar.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentacarApplication {

	private final UserService userService;

	public RentacarApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RentacarApplication.class, args);
	}

	// Uygulama başlatıldığında bir admin kullanıcı oluşturulur
	@Bean
	CommandLineRunner createAdminUser() {
		return args -> {
			if (userService.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword("admin123");
				admin.setRole("ADMIN");
				userService.saveUser(admin);
				System.out.println("Admin kullanıcı oluşturuldu: admin/admin123");
			}
		};
	}
}
