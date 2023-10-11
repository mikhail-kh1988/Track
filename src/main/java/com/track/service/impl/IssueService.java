package com.track.service.impl;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.UpdateIssueDto;
import com.track.entity.Project;
import com.track.entity.issue.Attachment;
import com.track.entity.issue.Issue;
import com.track.entity.issue.TimeCost;
import com.track.repository.IssueRepository;
import com.track.repository.ProjectRepository;
import com.track.repository.TimeCostRepository;
import com.track.repository.TrackRepository;
import com.track.service.IIssueService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueService implements IIssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TimeCostRepository timeCostRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private IUserService userService;

    @Override
    public boolean createNewIssue(IssueDto dto) {

        Issue issue = new Issue();
        issue.setCreateDate(LocalDateTime.now());
        issue.setShortDescription(dto.getShortDescription());
        issue.setDescriptionBody(dto.getDescriptionBody());
        issue.setPriority(dto.getPriority());




        return false;
    }

    private String createExternalId(Project project){
        String name = project.getName();
        String template = "";

        if (name.length() <= 3){
            return template = name+"-"+template;

        }

        return "";
    }

    @Override
    public boolean changeIssue(UpdateIssueDto dto) {
        return false;
    }

    @Override
    public boolean deleteIssue(String externalId) {
        return false;
    }

    @Override
    public Issue findIssueByExternalId(String externalId) {
        return null;
    }

    @Override
    public boolean bindIssues(String parentId, String childId) {
        return false;
    }

    @Override
    public void addCommentInIssue(CommentDto dto) {

    }

    @Override
    public void addResponseComment(CommentDto dto, Long commentId) {

    }

    @Override
    public boolean addAttachmentInIssue(String externalId, Attachment attachment) {
        return false;
    }

    @Override
    public boolean addTimeCostInIssue(String externalId, TimeCost cost) {
        return false;
    }

    @Override
    public boolean removeTimeCostFromIssue(String externalId, Long costId) {
        return false;
    }

    @Override
    public boolean removeAttachmentFromIssue(Long attachId) {
        return false;
    }

    @Override
    public List<Issue> findIssueByProjectId(Long projectId) {
        return null;
    }

    @Override
    public List<Issue> findBindIssue(String externalId) {
        return null;
    }

    @Override
    public List<Issue> findIssueByField(int priorityId, Long statusId, Long categoryId, String trackName, Long assignId, Long createById) {
        return null;
    }

    @Override
    public List<Issue> findIssueByBetweenDate(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public boolean assignUser(String externalId, Long userId) {
        return false;
    }

    @Override
    public boolean assignGroup(String externalId, Long groupId) {
        return false;
    }

    @Override
    public void nextStatusIssue(String externalId) {

    }

    @Override
    public void previousStatusIssue(String externalId) {

    }
}
