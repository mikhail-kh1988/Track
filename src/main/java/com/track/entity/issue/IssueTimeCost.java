package com.track.entity.issue;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "issues_time_cost")
public class IssueTimeCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issue_id")
    private Issue issues;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_cost_id")
    private TimeCost timeCost;

    private LocalDateTime createDate;

}
