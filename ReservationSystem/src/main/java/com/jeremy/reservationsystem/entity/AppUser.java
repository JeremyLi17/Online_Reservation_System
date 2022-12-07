package com.jeremy.reservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

/**
 * @author jeremy on 2022/12/6
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uniqueEmail",
                columnNames = {"email"}
        )
)
public class AppUser {
    @Id
    @SequenceGenerator(
            name = "USER_GENERATOR",
            sequenceName = "USER_GENERATOR",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "USER_GENERATOR",
            strategy = AUTO
    )
    @Column(name = "id", updatable = false)
    private Integer id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user",
            fetch = LAZY,
            cascade = ALL
    )
    @JsonManagedReference
    private List<Reservation> reservations;

    public AppUser(String username,
                   String lastName,
                   String firstName,
                   String email) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }
}
