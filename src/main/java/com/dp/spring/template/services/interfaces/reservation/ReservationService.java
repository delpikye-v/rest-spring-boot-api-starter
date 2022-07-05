package com.dp.spring.template.services.interfaces.reservation;

import com.dp.spring.template.models.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation create(Reservation reservation);

    List<Reservation> findAll();

    Reservation findByCode(String code);
}
