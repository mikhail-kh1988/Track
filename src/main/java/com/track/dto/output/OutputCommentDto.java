package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OutputCommentDto {

    private String login;
    private String body;
    private LocalDateTime createDate;

}
