package com.track.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users_groups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groups_id")
    private Group groups;

    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private User createBy;

}
