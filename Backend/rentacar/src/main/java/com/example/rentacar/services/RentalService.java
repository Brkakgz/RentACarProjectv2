package com.example.rentacar.services;

import com.example.rentacar.models.Car;
import com.example.rentacar.models.Rental;
import com.example.rentacar.models.User;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
    }

    public Rental createRental(User user, Car car, Rental rental) {
        if (car == null || car.getId() == null) {
            throw new IllegalArgumentException("Geçersiz araba.");
        }

        // Toplam fiyat hesaplama
        long rentalDays = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        rental.setTotalPrice(rentalDays * car.getDailyPrice());

        rental.setUser(user);
        rental.setCar(car);
        rental.setCompleted(false);

        // Arabayı kiralanmış olarak işaretle (manuel işlemler)
        carRepository.save(car);

        return rentalRepository.save(rental);
    }

    public List<Rental> getUserRentals(User user) {
        return rentalRepository.findByUser(user);
    }

    public void completeRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Kiralama bulunamadı."));
        rental.setCompleted(true);
        rentalRepository.save(rental);
    }

    /**
     * Kullanıcının aktif (tamamlanmamış) kiralamalarını getirir.
     */
    public List<Rental> getActiveRentals(User user) {
        return rentalRepository.findByUserAndIsCompleted(user, false);
    }

    /**
     * Kullanıcının tamamlanmış kiralamalarını getirir.
     */
    public List<Rental> getCompletedRentals(User user) {
        return rentalRepository.findByUserAndIsCompleted(user, true);
    }

}
