package com.jeremy.reservationsystem.service;

import com.jeremy.reservationsystem.entity.Reservation;
import com.jeremy.reservationsystem.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jeremy on 2022/12/6
 */
@Service
public class ReservationService {

    private final ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public void makeReservation(Reservation reservation) {
        reservationRepo.save(reservation);
    }

    public void cancelReservation(Integer reserveId) {

    }

    public List<Reservation> getReservationByUserId(Integer userId) {
        return reservationRepo.getReservationByUserId(userId);
    }
}
