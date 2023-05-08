package com.track.service;

import com.track.dto.ChangeDateSprintDto;
import com.track.dto.SprintDto;
import com.track.entity.Issue;
import com.track.entity.Sprint;

import java.time.LocalDate;
import java.util.List;

public interface ISprintService {

    void createNewSprint(SprintDto dto);
    boolean deleteSprintById(long id);
    boolean addIssueInSprint(Long issueId, Long sprintId);
    boolean deleteIssueFromSprint(Long issueId, Long sprintId);
    boolean changeDateIntoSprint(Long sprintId, ChangeDateSprintDto dto);
    List<Issue> getIssuesFromSprintById(Long id);
    List<Issue> getIssuesFromSprintByName(String name);
    List<Sprint> getSprintsToday(LocalDate date);
}
