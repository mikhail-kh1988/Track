package com.track.dto;

import com.track.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class StatusDto {

    private Long id;
    private String name;
    private int order;
    private Long project_id;
    private boolean closed;
}
