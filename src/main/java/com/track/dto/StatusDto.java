package com.track.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDto {

    private Long id;
    private String name;
    private int order;
    private Long project_id;
    private boolean closed;
}
