package com.track.entity.pages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.track.entity.Project;
import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User adminBy;
    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;
    private boolean stared;
    private boolean openComment;
    private boolean childPage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Page child;
    private String shortNamePage;
    private String body;

    @JsonIgnore
    @OneToMany(mappedBy = "pages")
    private List<PageChildPage> pageChildPages;

    // --- --- --- ---
    //private List<UserPage> userPages;
    //private List<GroupPage> groupPages;

}
