package com.track.entity.issue;

import com.track.entity.Group;
import com.track.entity.Project;
import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "projects_groups")
public class ProjectGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groups_id")
    private Group groups;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projects_id")
    private Project project;

    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private User createBy;

}
