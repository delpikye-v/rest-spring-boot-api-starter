package com.dp.spring.template.services.implement.reservation;

import com.dp.spring.template.services.interfaces.reservation.ReservationService;
import com.dp.spring.template.exceptions.NotFoundException;
import com.dp.spring.template.repositories.reservation.ReservationRepository;
import com.dp.spring.template.services.implement.base.BaseService;
import com.dp.spring.template.models.reservation.Reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends BaseService implements ReservationService {
    /*@Autowired
    private final ReservationRepository reservationRepository;*/

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    private String generateCode() {
        Optional<Reservation> reservation = reservationRepository.findFirstByOrderByIdDesc();
        int lastId = reservation.map(Reservation::getId).orElse(0);
        return "RSV-" + Calendar.getInstance().get(Calendar.YEAR) + "-" + (1000 + lastId + 1);
    }

    @Override
    public Reservation create(Reservation reservation) {
        reservation.setCode(generateCode());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    @Override
    public Reservation findByCode(String code) {
        return reservationRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Not found Reservation with code = " + code));
    }
}
