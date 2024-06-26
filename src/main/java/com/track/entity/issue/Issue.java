package com.track.entity.issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.track.entity.*;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Group assignGroup;

    private LocalDateTime createDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime lastChangeDate;
    private boolean closed;
    private boolean lose;
    private boolean parent;
    private String version;
    private int state;
    private int planingTimeCost;
    private int actualTimeCost;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<IssueComment> issueComment;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<IssueBind> issuesListBind;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<SprintIssue> sprintIssue;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<IssueAttachment> issueAttachments;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<IssueTimeCost> issueTimeCosts;

    @JsonIgnore
    @OneToMany(mappedBy = "issues")
    private List<IssueAction> issueActions;

}
