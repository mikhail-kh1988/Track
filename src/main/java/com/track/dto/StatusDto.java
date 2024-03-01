package com.track.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDto {

    private String name;
    private int order;
    private Long project_id;
    private Long group_id;
    private boolean closed;
}
