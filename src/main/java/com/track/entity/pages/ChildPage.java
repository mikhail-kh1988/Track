package com.track.entity.pages;

import com.track.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "childpages")
public class ChildPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User createBy;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User adminBy;

    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;
    private boolean stared;
    private boolean openComment;
    private String shortName;
    private String body;

    /*@OneToMany(mappedBy = "childpages")
    List<PageChildPage> pageChildPageList;*/
}
