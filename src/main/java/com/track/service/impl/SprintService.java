package com.track.service.impl;

import com.track.dto.ChangeDateSprintDto;
import com.track.dto.SprintDto;
import com.track.dto.output.Track;
import com.track.entity.Sprint;
import com.track.entity.SprintIssue;
import com.track.entity.issue.Issue;
import com.track.exception.NotFoundIssue;
import com.track.exception.NotFoundIssueFromSprint;
import com.track.exception.NotFoundSprintException;
import com.track.repository.SprintIssueRepository;
import com.track.repository.SprintRepository;
import com.track.service.IIssueService;
import com.track.service.ISprintService;
import com.track.service.IUserService;
import com.track.tools.IssueToTrackTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SprintService implements ISprintService {


    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private SprintIssueRepository sprintIssueRepository;

    @Autowired
    private IIssueService iIssueService;

    @Autowired
    private IUserService userService;


    @Override
    public void createNewSprint(SprintDto dto) {

        Sprint sprint = new Sprint();

        sprint.setName(dto.getName());
        sprint.setUser(userService.findUserById(dto.getCreateById()));
        sprint.setEndDate(dto.getEndDate());
        sprint.setStartDate(dto.getStartDate());
        sprint.setCreateDate(LocalDateTime.now());

        sprintRepository.save(sprint);
    }

    @Override
    public boolean deleteSprintById(long id) {

        if (sprintRepository.findById(id).isEmpty())
            throw new NotFoundSprintException("Sprint not found!");

        Sprint sprint = sprintRepository.findById(id).get();

        sprint.setUser(null);

        sprintRepository.delete(sprint);

        return true;
    }

    @Override
    public boolean addIssueInSprint(String issueId, Long sprintId) {
        SprintIssue sprintIssue = new SprintIssue();

        if(iIssueService.findIssueInternalByExternalId(issueId) == null)
            throw new NotFoundIssue("Issue not found!");

        if (sprintRepository.findById(sprintId).isEmpty())
            throw new NotFoundSprintException("Sprint not found!");

        Issue issue = iIssueService.findIssueInternalByExternalId(issueId);

        Sprint sprint = sprintRepository.findById(sprintId).get();

        sprintIssue.setIssues(issue);
        sprintIssue.setSprints(sprint);
        sprintIssue.setCreateDate(LocalDateTime.now());

        sprintIssueRepository.save(sprintIssue);

        return true;
    }

    @Override
    public boolean deleteIssueFromSprint(String issueId, Long sprintId) {

        Issue issue = iIssueService.findIssueInternalByExternalId(issueId);

        Sprint sprint = sprintRepository.findById(sprintId).get();

        for (SprintIssue si: sprint.getSprintsIssue()) {
            if (si.getIssues().getExternalId().equals(issue.getExternalId())) {
                si.setIssues(null);
                si.setSprints(null);
                sprintIssueRepository.save(si);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean changeDateIntoSprint(Long sprintId, ChangeDateSprintDto dto) {

        if (sprintRepository.findById(sprintId).isEmpty())
            throw new NotFoundSprintException("Sprint not found!");

        Sprint sprint = sprintRepository.findById(sprintId).get();

        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());

        sprintRepository.save(sprint);

        return true;
    }

    @Override
    public List<Track> getIssuesFromSprintById(Long id) {

        List<Track> issueList = new ArrayList<>();

        List<SprintIssue> sprintsIssue = sprintRepository.findById(id).get().getSprintsIssue();

        if (sprintsIssue == null)
            throw new NotFoundIssueFromSprint("No issue from sprint");

        for (SprintIssue si: sprintsIssue){
            issueList.add(IssueToTrackTranslator.getTrack(si.getIssues()));
        }

        return issueList;
    }

    @Override
    public List<Track> getIssuesFromSprintByName(String name) {
        Sprint sprint = sprintRepository.findByName(name);
        List<Track> tracks = new ArrayList<>();

        if (sprint.getSprintsIssue().isEmpty())
            throw new NotFoundIssueFromSprint("No issue from sprint!");

        for (SprintIssue si: sprint.getSprintsIssue())
            tracks.add(IssueToTrackTranslator.getTrack(si.getIssues()));

        return tracks;
    }

    @Override
    public List<Track> getSprintsToday(LocalDate date) {

        List<Track> tracks = new ArrayList<>();



        return null;
    }
}
