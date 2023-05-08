package com.track.service;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.UpdateIssueDto;
import com.track.entity.Issue;
import java.util.List;

public interface IIssueService {

    boolean createNewIssue(IssueDto dto);
    boolean changeIssue(UpdateIssueDto dto);
    boolean deleteIssue(String externalId);
    Issue findIssueByExternalId(String externalId);
    boolean bindIssues(String parentId, String childId);
    void addCommentInIssue(CommentDto dto);
    List<Issue> findIssueByProjectId(Long projectId);
    List<Issue> findIssueByField(int priorityId, Long statusId, Long categoryId, String trackName);
    List<Issue> findIssueByAssign(Long assignId);
    void nextStatusIssue(String externalId);
    void previousStatusIssue(String externalId);
}
