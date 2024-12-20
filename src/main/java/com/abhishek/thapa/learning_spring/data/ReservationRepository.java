package com.abhishek.thapa.learning_spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Iterable<Reservation> findReservationByResDate(Date date); // function that will get reservation Entity if Date= argument passed date
}
