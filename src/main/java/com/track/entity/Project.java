package com.track.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.track.entity.issue.ProjectGroup;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String prefix;
    private LocalDateTime createDate;
    private int type;

    @JsonIgnore
    @OneToMany(mappedBy = "groups")
    private List<ProjectGroup> groups;

}
