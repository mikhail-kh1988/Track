/*
package com.track.entity.issue;

import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "time_cost")
public class TimeCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Issue issue;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

    private String comment;
    private int time;
    private LocalDate date;
    private LocalDateTime createDate;
    private boolean day;
    private boolean hours;
    private boolean minutes;
    private boolean seconds;


}
*/
