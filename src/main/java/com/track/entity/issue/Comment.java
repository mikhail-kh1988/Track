package com.track.entity.issue;

import com.track.entity.User;
import com.track.entity.issue.IssueComment;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    private LocalDateTime createDate;
    private String body;
    private boolean response;
    private Long commentId;

    @OneToMany(mappedBy = "comments")
    private List<IssueComment> issueCommentList;

}
