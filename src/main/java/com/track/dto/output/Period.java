package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Period {

    private long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private List<Track> trackList;

}
