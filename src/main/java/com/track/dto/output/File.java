package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class File {

    private String name;
    private String path;
    private LocalDateTime createDate;
}
