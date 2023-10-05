package com.track.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
//@Table(name = "rfc")
public class RFC {

    private Long id;
    private String name;
    private String version;
    private Project project;
    private LocalDateTime createDate;
    private LocalDateTime planingStartWorkDate;
    private LocalDateTime planingEndWorkDate;
    private LocalDateTime closeDate;


}
