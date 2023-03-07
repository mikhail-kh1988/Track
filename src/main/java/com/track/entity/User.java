package com.track.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String name;

    private String email;
    private String login;
    private String password;
    private String jobTitle;
    private String phoneNumber;
    private String cnAdUser;
    private int status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

}
