package com.track.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "status")
public class Status {

    @Id
    private Long id;
    private String name;
    private int orders;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Project project;

}