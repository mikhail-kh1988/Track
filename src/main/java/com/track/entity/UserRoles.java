package com.track.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id")
    private Role roles;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

    private boolean active;
}
