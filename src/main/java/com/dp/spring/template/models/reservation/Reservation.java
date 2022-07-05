package com.dp.spring.template.models.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dp.spring.template.models.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Table(name = "reservations")
@Entity
@Data
@Accessors(chain = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 20, unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private int bagsCount;

    @Column(nullable = false)
    private Date departureDate;

    @Column(nullable = false)
    private Date arrivalDate;

    @Column(nullable = false)
    private int roomNumber;

    @Column(nullable = false)
    private String[] extras;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference(value = "user-reservations")
    private User user;

    @Lob
    @Column
    private String note;

    public Reservation(
            int bagsCount,
            Date departureDate,
            Date arrivalDate,
            int roomNumber,
            String[] extras,
            String note
    ) {
        this.bagsCount = bagsCount;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.roomNumber = roomNumber;
        this.extras = extras;
        this.note = note;
    }

    public Reservation() {

    }
}
