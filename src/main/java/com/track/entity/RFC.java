package com.track.entity;

import com.track.entity.issue.Issue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
//@Entity
//@Table(name = "rfc")
public class RFC {

    private Long id;
    private String name;
    private String version;
    private Project project;
    private String shortDescription;
    private String fullDescription;
    private User createBy;
    private User asigneUser;
    private LocalDateTime createDate;
    private LocalDateTime planingStartWorkDate;
    private LocalDateTime planingEndWorkDate;
    private LocalDateTime actualStartWorkDate;
    private LocalDateTime actualEndWorkDate;
    private LocalDateTime closeDate;
    private List<User> appruvUsers;
    private List<Issue> issueList;



}
