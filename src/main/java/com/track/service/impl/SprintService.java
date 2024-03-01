package com.track.service.impl;

import com.track.dto.ChangeDateSprintDto;
import com.track.dto.SprintDto;
import com.track.entity.Sprint;
import com.track.entity.issue.Issue;
import com.track.repository.SprintIssueRepository;
import com.track.repository.SprintRepository;
import com.track.service.ISprintService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SprintService implements ISprintService {


    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private SprintIssueRepository sprintIssueRepository;

    @Autowired
    private IUserService userService;


    @Override
    public void createNewSprint(SprintDto dto) {

        Sprint sprint = new Sprint();

        sprint.setName(dto.getName());
        sprint.setUser(userService.findUserById(dto.getCreateById()));
        sprint.setEndDate(dto.getEndDate());
        sprint.setStartDate(dto.getStartDate());

        sprintRepository.save(sprint);
    }

    @Override
    public boolean deleteSprintById(long id) {
        return false;
    }

    @Override
    public boolean addIssueInSprint(Long issueId, Long sprintId) {
        return false;
    }

    @Override
    public boolean deleteIssueFromSprint(Long issueId, Long sprintId) {
        return false;
    }

    @Override
    public boolean changeDateIntoSprint(Long sprintId, ChangeDateSprintDto dto) {
        return false;
    }

    @Override
    public List<Issue> getIssuesFromSprintById(Long id) {
        return null;
    }

    @Override
    public List<Issue> getIssuesFromSprintByName(String name) {
        return null;
    }

    @Override
    public List<Sprint> getSprintsToday(LocalDate date) {
        return null;
    }
}
