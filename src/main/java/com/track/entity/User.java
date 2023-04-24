package com.track.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<UserRole> roleList;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserGroup> groupList;

}
