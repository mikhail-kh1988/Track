package com.track.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OutputIssueDto {

    private String externalId;
    private String trackName;
    private String shortDescription;
    private String description;
    private int priority;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;
    private LocalDateTime endDate;
    private boolean lose;
    private boolean parent;
    private List<OutputCommentDto> comments;

}
