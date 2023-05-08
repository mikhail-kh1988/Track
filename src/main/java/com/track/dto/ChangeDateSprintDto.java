package com.track.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChangeDateSprintDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
