package com.dp.spring.template.controllers.reservation;

import com.dp.spring.template.controllers.base.BaseController;
import com.dp.spring.template.dtos.reservation.CreateReservationDto;
import com.dp.spring.template.models.reservation.Reservation;
import com.dp.spring.template.models.user.User;
import com.dp.spring.template.services.interfaces.reservation.ReservationService;
import com.dp.spring.template.services.interfaces.user.UserService;
import com.dp.spring.template.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RequestMapping(value = "/reservations")
@RestController
public class ReservationController extends BaseController {
    private final ReservationService reservationService;

    private final UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody CreateReservationDto createReservationDto)
            throws NotFoundException {
        User user = userService.findById(createReservationDto.getUserId());
        Reservation reservationInput = createReservationDto.toReservation().setUser(user);
        Reservation createdReservation = reservationService.create(reservationInput);

        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Reservation>> allReservations() {
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Reservation> oneReservation(
            @Pattern(
                    regexp = "^RSV(-\\d{4,}){2}$",
                    message = "The reservation code is invalid"
            ) @PathVariable String code
    ) throws NotFoundException {
        return new ResponseEntity<>(reservationService.findByCode(code), HttpStatus.OK);
    }
}
