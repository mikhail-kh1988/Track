package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Note {

    private String login;
    private String fullName;
    private String body;
    private LocalDateTime createDate;
    private boolean response;

}
