package com.track.dto.output;

import com.track.entity.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Track {

    private String externalId;
    private String trackName;
    private String shortDescription;
    private String description;
    private int priority;
    private String priorityName;
    private String status;
    private long statusId;
    private String projectName;
    private long projectId;
    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean lose;
    private boolean closed;
    private boolean parent;
    private String categoryName;
    private long categoryId;
    private String assignName;
    private long assignId;
    private String assignGroupName;
    private long assignGroupId;
    private String version;
    private String state;
    private String timeCostName;
    private int planingTimeCost;
    private int actualTimeCost;
    private List<Note> notes;
    private List<File> files;
    private List<Track> bindTrack;
    private List<Activity> activities;
    private List<PlaningTime> planingTimes;


}
