package com.track.service.impl;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.UpdateIssueDto;
import com.track.dto.output.Track;
import com.track.entity.Group;
import com.track.entity.Project;
import com.track.entity.Status;
import com.track.entity.User;
import com.track.entity.issue.*;

import com.track.exception.NotFoundIssue;
import com.track.repository.*;
import com.track.service.IIssueService;
import com.track.service.IProjectService;
import com.track.service.IUserService;
import com.track.tools.IssueToTrackTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService implements IIssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private BindIssueRepository bindIssueRepository;

    @Autowired
    private CommentIssueRepository commentIssueRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /*@Autowired
    private TimeCostRepository timeCostRepository;*/

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private IUserService userService;
    
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public boolean createNewIssue(IssueDto dto) {

        Status status = null;
        Issue issue = new Issue();
        issue.setCreateDate(LocalDateTime.now());
        issue.setShortDescription(dto.getShortDescription());
        issue.setDescriptionBody(dto.getDescriptionBody());
        
        //Check priority
        int tempPriority = dto.getPriority();
        
        if (tempPriority > 3) {
            issue.setPriority(3);
        }else {
            issue.setPriority(dto.getPriority());
        }
        
        // Check user
        Long tempUserId = userService.findUserById(dto.getCreateByUserId()).getId();
        
        if (tempUserId == null) {
            issue.setCreateBy(userService.findUserById(2L));
        }else {
            issue.setCreateBy(userService.findUserById(dto.getCreateByUserId()));
        }
        
        issue.setProject(projectService.findProjectByID(dto.getProjectId()));
        issue.setTrackName(trackRepository.findById(dto.getTrackId()).get().getName());
        issue.setExternalId(createExternalId(projectService.findProjectByID(dto.getProjectId())));

        for (Status st: projectService.getStatusesByProject(dto.getProjectId())) {
            if (st.getOrders() == 1){
                status = st;
            }
        }

        issue.setStatus(status);

        issueRepository.save(issue);

        return false;
    }

    private String createExternalId(Project project){
        String templateId = "";

        if(issueRepository.findByLastRecord().isPresent()){
            String lastID = issueRepository.findByLastRecord().get().getExternalId();

            String str[] = lastID.split("-");


            Long ID = Long.parseLong(str[1]);

            ID++;

            templateId = project.getPrefix()+"-"+ID;

        }else {
            String fix = project.getPrefix();
            templateId = fix + "-" + 1;
        }

        return templateId;
    }
    
    private boolean checkDtoBeforeChangeIssue(UpdateIssueDto dto){


        return false;

    }

    @Override
    public boolean changeIssue(UpdateIssueDto dto) {
        if (!dto.getExternalId().isEmpty()){
            if (issueRepository.findByExternalId(dto.getExternalId()) == null)
                throw new NotFoundIssue("");
        }

            Issue issue = issueRepository.findByExternalId(dto.getExternalId());

            if (dto.getPriority() > 3)
                issue.setPriority(3);
            issue.setPriority(dto.getPriority());

            if (dto.getCategoryId() != 0)
                issue.setCategory(categoryRepository.findById(dto.getCategoryId()).get());
            else
                issue.setCategory(null);

            if (dto.getStatusId() != 0)
                issue.setStatus(statusRepository.findById(dto.getStatusId()).get());
            else
                issue.setStatus(null);

            if (dto.getProjectId() != 0)
                issue.setProject(projectService.findProjectByID(dto.getProjectId()));
            else
                issue.setProject(null);

            if (!dto.getShortDescription().isEmpty())
                issue.setShortDescription(dto.getShortDescription());

            if (!dto.getDescriptionBody().isEmpty())
                issue.setDescriptionBody(dto.getDescriptionBody());

            if (!dto.getResolution().isEmpty()) {
                issue.setClosed(true);
                issue.setResolution(dto.getResolution());
            }

            if (dto.getState() != 0)
                issue.setState(dto.getState());
            else
                issue.setState(0);

            if (dto.getCreateByUserId() != 0)
                issue.setCreateBy(userService.findUserById(dto.getCreateByUserId()));
            else
                issue.setCreateBy(null);

            if (dto.getAssignId() != 0)
                issue.setAssign(userService.findUserById(dto.getAssignId()));

            if (dto.getAssignGroupId() != 0)
                issue.setAssignGroup(groupRepository.findById(dto.getAssignGroupId()).get());

            if (dto.getVersion() != null)
                issue.setVersion(dto.getVersion());

            issue.setLose(dto.isLose());
            issue.setStartDate(dto.getStartDate());
            issue.setEndDate(dto.getEndDate());
            issue.setLastChangeDate(LocalDateTime.now());

            issueRepository.save(issue);

            return true;

    }


    @Override
    public boolean deleteIssue(String externalId) {

        Issue issue = issueRepository.findByExternalId(externalId);

        issueRepository.delete(issue);

        return true;
    }

    @Override
    public Track findIssueByExternalId(String externalId) {
        return IssueToTrackTranslator.getTrack(issueRepository.findByExternalId(externalId));
    }

    @Override
    public boolean bindIssues(String parentId, String childId, Long userId) {

        Issue parentIssue = issueRepository.findByExternalId(parentId);

        Issue childIssue = issueRepository.findByExternalId(childId);

        User user = userService.findUserById(userId);

        IssueBind issueBinds = new IssueBind();
        issueBinds.setIssues(parentIssue);
        issueBinds.setChildIssue(childIssue);
        issueBinds.setCreateDate(LocalDateTime.now());
        issueBinds.setBindUser(user);

        bindIssueRepository.save(issueBinds);

        /*List<IssueBind> currentBindIssue = parentIssue.getIssuesListBind();
        if (currentBindIssue == null){
            currentBindIssue = new ArrayList<>();
        }

        currentBindIssue.add(issueBinds);

        parentIssue.setIssuesListBind(currentBindIssue);
        */

        if (!parentIssue.isParent()) {
            parentIssue.setParent(true);
        }

        issueRepository.save(parentIssue);

        return true;
    }

    @Override
    public void addCommentInIssue(CommentDto dto) {

        Issue issue = issueRepository.findByExternalId(dto.getExternalIdIssue());

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.now());
        comment.setBody(dto.getCommentBody());
        comment.setUser(userService.findUserById(dto.getUserId()));


        List<IssueComment> issueCommentList = issue.getIssueComment();

        if (issue.getIssueComment().isEmpty()){
            issueCommentList = new ArrayList<>();
        }

        IssueComment issueComment = new IssueComment();
        issueComment.setComments(comment);
        issueComment.setCreateDate(LocalDateTime.now());
        issueComment.setUser(userService.findUserById(dto.getUserId()));
        issueComment.setIssues(issue);

        issueCommentList.add(issueComment);
        issue.setIssueComment(issueCommentList);

        issueRepository.save(issue);
        commentRepository.save(comment);
        commentIssueRepository.save(issueComment);

    }

    @Override
    public void addResponseComment(CommentDto dto, Long commentId) {

        Issue issue = issueRepository.findByExternalId(dto.getExternalIdIssue());

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.now());
        comment.setBody(dto.getCommentBody());
        comment.setUser(userService.findUserById(dto.getUserId()));
        comment.setCommentId(commentId);


        List<IssueComment> issueCommentList = issue.getIssueComment();

        if (issue.getIssueComment().isEmpty()){
            issueCommentList = new ArrayList<>();
        }

        IssueComment issueComment = new IssueComment();
        issueComment.setComments(comment);
        issueComment.setCreateDate(LocalDateTime.now());
        issueComment.setUser(userService.findUserById(dto.getUserId()));

        issueCommentList.add(issueComment);
        issue.setIssueComment(issueCommentList);

        issueRepository.save(issue);
        commentRepository.save(comment);
        commentIssueRepository.save(issueComment);
    }

    @Override
    public boolean addAttachmentInIssue(String externalId, Attachment attachment) {
        return false;
    }

    /*@Override
    public boolean addTimeCostInIssue(String externalId, TimeCost cost) {
        return false;
    }*/

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
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public List<Issue> findBindIssue(String externalId) {

        Issue issue = issueRepository.findByExternalId(externalId);

        List<Issue> issueList = new ArrayList<>();

        for(IssueBind bi: issue.getIssuesListBind()){
            issueList.add(bi.getIssues());
        }

        return issueList;
    }

    @Override
    public List<Issue> findIssueByField(int priorityId, Long statusId, Long categoryId, String trackName, Long assignId, Long createById) {

        List<Issue> issueList = new ArrayList<>();

        issueList = issueRepository.findByTrackName(trackName);

        return null;
    }

    @Override
    public List<Issue> findIssueByBetweenDate(LocalDateTime start, LocalDateTime end) {



        return null;
    }

    @Override
    public boolean assignUser(String externalId, Long userId) {
        // Find Issue
        Issue issue = issueRepository.findByExternalId(externalId);
        if (issue == null){
              return false;
        }

        //Find User
        User user = userService.findUserById(userId);

        if (user == null){
            return false;
        }

        issue.setAssign(user);

        issueRepository.save(issue);

        return true;
    }

    @Override
    public boolean assignGroup(String externalId, Long groupId) {

        Issue issue = issueRepository.findByExternalId(externalId);
        if (issue == null){
              return false;
        }

        Group group = null;

        if (groupRepository.findById(groupId).isPresent()){

            group = groupRepository.findById(groupId).get();

        }else {
            return false;
        }

        issue.setAssignGroup(group);

        issueRepository.save(issue);

        return true;
    }

    @Override
    public void nextStatusIssue(String externalId) {

        Issue issue = issueRepository.findByExternalId(externalId);

        Status status = issue.getStatus();
        Status newStatus = null;

        int orderStatus = status.getOrders();

        orderStatus++;

        Project project = issue.getProject();

        List<Status> statusList = statusRepository.findByProjectId(project.getId());

        for (Status current: statusList){
            if (current.getOrders() == orderStatus){
                newStatus = current;

            }
        }

        issue.setStatus(newStatus);

        issueRepository.save(issue);

    }

    @Override
    public void previousStatusIssue(String externalId) {
        Issue issue = issueRepository.findByExternalId(externalId);

        Status status = issue.getStatus();
        Status newStatus = null;

        int orderStatus = status.getOrders();

        orderStatus--;

        Project project = issue.getProject();

        List<Status> statusList = statusRepository.findByProjectId(project.getId());

        for (Status current: statusList){
            if (current.getOrders() == orderStatus){
                newStatus = current;

            }
        }

        issue.setStatus(newStatus);

        issueRepository.save(issue);
    }
}
