package com.jeremy.reservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

/**
 * @author jeremy on 2022/12/6
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "uniqueTimeSlot",
                columnNames = {"room_id", "time_slot", "date"}
        )
})
public class Reservation {
    @Id
    @SequenceGenerator(
            name = "RESERVE_GENERATOR",
            sequenceName = "RESERVE_GENERATOR",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "RESERVE_GENERATOR",
            strategy = AUTO
    )
    @Column(updatable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private Date date;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    // 1: 8Am-10AM; 2: 10AM-12PM; 3: 2PM-4PM; 4: 4PM-6PM
    @Column(name = "time_slot", nullable = false)
    private Integer timeSlot;

    private String description;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "userId",
            nullable = false,
            referencedColumnName = "id"
    )
    @ToString.Exclude
    @JsonBackReference
    private AppUser user;

    public Reservation(Integer roomId,
                       Integer timeSlot,
                       String description,
                       AppUser user) {
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.description = description;
        this.user = user;
    }
}
