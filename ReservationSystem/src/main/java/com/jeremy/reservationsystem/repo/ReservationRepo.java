package com.jeremy.reservationsystem.repo;

import com.jeremy.reservationsystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jeremy on 2022/12/6
 */
@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    @Transactional
    @Query("SELECT r FROM Reservation r WHERE r.user.id = ?1")
    List<Reservation> getReservationByUserId(Integer userId);

    @Transactional
    @Query("SELECT r FROM Reservation r WHERE r.date >= current_date")
    List<Reservation> findAllValidReservation();
}
