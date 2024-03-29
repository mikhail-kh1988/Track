package com.track.entity;

import com.track.entity.issue.Issue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sprint_issue")
public class SprintIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sprint_id")
    private Sprint sprints;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issue_id")
    private Issue issues;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User addUserBy;

    private LocalDateTime createDate;


}
