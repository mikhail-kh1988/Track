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
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String name;
    private LocalDateTime createDate;

    @Column(name = "is_read")
    private boolean read;

    @Column(name = "is_write")
    private boolean write;

    @JsonIgnore
    @OneToMany(mappedBy = "roles")
    private List<UserRole> userRoleList;
}
