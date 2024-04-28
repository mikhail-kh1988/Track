package com.track.service;

import com.track.dto.ChangeDateSprintDto;
import com.track.dto.SprintDto;
import com.track.dto.output.Track;

import java.time.LocalDate;
import java.util.List;

public interface ISprintService {

    void createNewSprint(SprintDto dto);
    boolean deleteSprintById(long id);
    boolean addIssueInSprint(String issueId, Long sprintId);
    boolean deleteIssueFromSprint(String issueId, Long sprintId);
    boolean changeDateIntoSprint(Long sprintId, ChangeDateSprintDto dto);
    List<Track> getIssuesFromSprintById(Long id);
    List<Track> getIssuesFromSprintByName(String name);
    List<Track> getSprintsToday(LocalDate date);
}
