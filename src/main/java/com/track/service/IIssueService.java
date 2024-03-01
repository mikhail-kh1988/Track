package com.track.service;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.UpdateIssueDto;
import com.track.dto.output.Track;
import com.track.entity.issue.Attachment;
import com.track.entity.issue.Issue;


import java.time.LocalDateTime;
import java.util.List;

public interface IIssueService {

    boolean createNewIssue(IssueDto dto);
    boolean changeIssue(UpdateIssueDto dto);
    boolean deleteIssue(String externalId);
    Track findIssueByExternalId(String externalId);
    boolean bindIssues(String parentId, String childId, Long userId);
    void addCommentInIssue(CommentDto dto);
    void addResponseComment(CommentDto dto, Long commentId);
    boolean addAttachmentInIssue(String externalId, Attachment attachment);
    //boolean addTimeCostInIssue(String externalId, TimeCost cost);
    boolean removeTimeCostFromIssue(String externalId, Long costId);
    boolean removeAttachmentFromIssue(Long attachId);
    List<Issue> findIssueByProjectId(Long projectId);
    List<Issue> findBindIssue(String externalId);
    List<Issue> findIssueByField(int priorityId, Long statusId, Long categoryId, String trackName, Long assignId, Long createById);
    List<Issue> findIssueByBetweenDate(LocalDateTime start, LocalDateTime end);
    boolean assignUser(String externalId, Long userId);
    boolean assignGroup(String externalId, Long groupId);
    void nextStatusIssue(String externalId);
    void previousStatusIssue(String externalId);
}
