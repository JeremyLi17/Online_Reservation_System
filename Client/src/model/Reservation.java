package model;

import java.util.Date;

/**
 * @author jeremy on 2022/12/6
 */
public class Reservation {

    private Integer id;
    private Date date;
    private Integer roomId;
    private Integer timeSlot;
    private String description;
    private AppUser user;

    public Reservation() {}

    public Reservation(Integer id,
                       Date date,
                       Integer roomId,
                       Integer timeSlot,
                       String description,
                       AppUser user) {
        this.id = id;
        this.date = date;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", roomId=" + roomId +
                ", timeSlot=" + timeSlot +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
