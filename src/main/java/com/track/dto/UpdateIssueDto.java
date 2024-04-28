package com.track.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateIssueDto {

    private String externalId;
    private String shortDescription;
    private String descriptionBody;
    private String resolution;
    private int priority;
    private long projectId;
    private int createByUserId;
    private int trackId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int state;
    private boolean lose;
    private long categoryId;
    private long statusId;
    private long assignId;
    private long assignGroupId;
    private String version;
    private long changeByUserId;
    private int planingTimeCost;

}
