package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlaningTime {

    private String userFio;
    private String comment;
    private String time;
    private LocalDate dateStart;
    private LocalDate dateStop;

}
