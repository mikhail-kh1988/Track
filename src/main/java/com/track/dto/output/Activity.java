package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Activity {

    private String action;
    private LocalDateTime createDate;

}
