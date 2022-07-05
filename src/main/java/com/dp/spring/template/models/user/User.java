package com.dp.spring.template.models.user;

import com.dp.spring.template.models.address.Address;
import com.dp.spring.template.models.reservation.Reservation;
import com.dp.spring.template.models.role.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Entity
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    /*@Column(length = 100, nullable = false)*/
    @Column(length = 100)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    /*@Column(nullable = false)*/
    private Date birthDate;

    /*@Column(length = 10, nullable = false)*/
    @Column(length = 10)
    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "user")
    @JsonManagedReference(value = "user-reservations")
    private List<Reservation> reservations;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
