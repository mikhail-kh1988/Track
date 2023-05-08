package com.track.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SprintDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private int createById;

}
