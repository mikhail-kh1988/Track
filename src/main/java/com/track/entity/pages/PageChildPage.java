package com.track.entity.pages;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pages_childpages")
public class PageChildPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pages_id")
    private Page pages;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "childpages_id")
    private ChildPage childPages;

    private LocalDateTime createDate;
}
