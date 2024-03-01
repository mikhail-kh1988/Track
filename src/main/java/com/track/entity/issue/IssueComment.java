package com.track.entity.issue;

import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments_issues")
public class IssueComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issues_id")
    private Issue issues;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comments_id")
    private Comment comments;

    private LocalDateTime createDate;

}
