package model;

import java.util.List;

/**
 * @author jeremy on 2022/12/6
 */
public class AppUser {

    private Integer id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private List<Reservation> reservations;

    public AppUser() {}

    public AppUser(Integer id,
                   String username,
                   String lastName,
                   String firstName,
                   String email,
                   List<Reservation> reservations) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
