package com.track.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueDto {

    private String shortDescription;
    private String descriptionBody;
    private int priority;
    private long projectId;
    private long createByUserId;
    private long trackId;

}
