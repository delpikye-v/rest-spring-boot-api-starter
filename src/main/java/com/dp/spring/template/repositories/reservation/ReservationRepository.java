package com.dp.spring.template.repositories.reservation;

import com.dp.spring.template.models.reservation.Reservation;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    Optional<Reservation> findFirstByOrderByIdDesc();

    Optional<Reservation> findByCode(String code);
}

