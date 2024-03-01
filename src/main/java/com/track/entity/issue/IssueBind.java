package com.track.entity.issue;

import com.track.entity.User;
import com.track.entity.issue.Issue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bind_issue")
public class IssueBind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User bindUser;
    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issue_id")
    private Issue issues;


    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "child_issue_id")
    private Issue childIssue;
}
