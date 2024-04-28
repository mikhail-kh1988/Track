package com.track.entity.issue;

import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    private String describe;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

}
