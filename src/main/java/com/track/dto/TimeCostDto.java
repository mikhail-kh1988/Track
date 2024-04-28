package com.track.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TimeCostDto {

    private String externalId;
    private String time;
    private LocalDate dateStart;
    private LocalDate dateStop;
    private String comment;
    private long createByUserId;

}
