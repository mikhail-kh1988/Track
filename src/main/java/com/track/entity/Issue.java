package com.track.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    private String trackName;
    private String shortDescription;
    private String descriptionBody;
    private String resolution;
    private int priority;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User assign;

    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime lastChangeDate;
    private LocalDateTime endDate;
    private boolean lose;
    private boolean parent;
    private String version;
    private int state;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<CommentIssue> commentIssueList;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<BindIssue> bindIssuesList;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<SprintIssue> sprintIssueList;

}
