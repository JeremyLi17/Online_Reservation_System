package com.jeremy.reservationsystem.controller;

import com.jeremy.reservationsystem.entity.Reservation;
import com.jeremy.reservationsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jeremy on 2022/12/6
 */
@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void makeReservation(@RequestBody Reservation reservation) {
        reservationService.makeReservation(reservation);
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservation() {
        return reservationService.getAllReservation();
    }

    @GetMapping
    public List<Reservation> getReservationByUserId(@RequestParam Integer userId) {
        return reservationService.getReservationByUserId(userId);
    }

    @DeleteMapping
    public void cancelReservation(@RequestParam Integer reserveId) {
        reservationService.cancelReservation(reserveId);
    }
}
