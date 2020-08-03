package com.onlineshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer userId;

    private String username;
    private String password;
    private String email;

    @Column(name = "full_name")
    private String fullName;

    private String city;

    private String address;

    @Column(name = "phone")
    private String phoneNumber;

    private String gender;
    private LocalDate birthday;

    @Column(name = "start_date")
    private LocalDate startDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Order> orders;
}
