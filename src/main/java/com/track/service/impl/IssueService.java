package com.track.service.impl;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.TimeCostDto;
import com.track.dto.UpdateIssueDto;
import com.track.dto.output.Track;
import com.track.entity.*;
import com.track.entity.issue.*;

import com.track.exception.NotFoundGroupException;
import com.track.exception.NotFoundIssue;
import com.track.exception.NotFoundRoleUserException;
import com.track.exception.NotFoundUserException;
import com.track.repository.*;
import com.track.service.IActionService;
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

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private IActionService actionService;

    @Autowired
    private TimeCostRepository timeCostRepository;

    @Autowired
    private IssueTimeCostRepository issueTimeCostRepository;

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

        actionService.addAction("New Issue registered, by user "+userService.findUserById(dto.getCreateByUserId()).getName()+" .", dto.getCreateByUserId(), issue.getExternalId());
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

    @Override
    public boolean changeIssue(UpdateIssueDto dto) {
        if (!dto.getExternalId().isEmpty()){
            if (issueRepository.findByExternalId(dto.getExternalId()) == null)
                throw new NotFoundIssue("");
        }

        Issue issue = issueRepository.findByExternalId(dto.getExternalId());

        if (dto.getPriority() > 4) {
            issue.setPriority(4);
        }else {
            changePriority(issue, dto.getPriority(), dto.getChangeByUserId());
        }
        if (dto.getCategoryId() != 0) {
            String oldCategoryName = issue.getCategory().getName();
            issue.setCategory(categoryRepository.findById(dto.getCategoryId()).get());
            actionService.addAction("Change category Issue, old value \" "+oldCategoryName+" \", new value \" "+categoryRepository.findById(dto.getCategoryId()).get().getName()+" \" change by user " + userService.findUserById(dto.getChangeByUserId()).getName() + " .", dto.getChangeByUserId(), issue.getExternalId());
        }else {
            issue.setCategory(null);
        }
        if (dto.getStatusId() != 0) {
            issue.setStatus(statusRepository.findById(dto.getStatusId()).get());
            actionService.addAction("Change status Issue, by user " + userService.findUserById(dto.getChangeByUserId()).getName() + " .", dto.getChangeByUserId(), issue.getExternalId());
        }else {
            issue.setStatus(null);
        }
        if (dto.getProjectId() != 0) {
            changeProject(issue, dto.getProjectId(), dto.getChangeByUserId());
        }else {
            issue.setProject(null);
        }
        if (!dto.getShortDescription().isEmpty()) {
            issue.setShortDescription(dto.getShortDescription());
            actionService.addAction("Change short description Issue, by user " + userService.findUserById(dto.getChangeByUserId()).getName() + " .", dto.getChangeByUserId(), issue.getExternalId());
        }
        if (!dto.getDescriptionBody().isEmpty()) {
            issue.setDescriptionBody(dto.getDescriptionBody());
            actionService.addAction("Change description body Issue, by user "+userService.findUserById(dto.getChangeByUserId()).getName()+" .", dto.getChangeByUserId(), issue.getExternalId());
        }
        if (!dto.getResolution().isEmpty()) {
            issue.setClosed(true);
            issue.setResolution(dto.getResolution());
            actionService.addAction("Add resolution by user "+userService.findUserById(dto.getChangeByUserId()).getName()+" .", dto.getChangeByUserId(), issue.getExternalId());
        }

        if (dto.getState() != 0) {
            issue.setState(dto.getState());
            actionService.addAction("Change state Issue, by user " + userService.findUserById(dto.getChangeByUserId()).getName() + " .", dto.getChangeByUserId(), issue.getExternalId());
        }else {
            issue.setState(0);
        }
        if (dto.getCreateByUserId() != 0) {
            issue.setCreateBy(userService.findUserById(dto.getCreateByUserId()));
            actionService.addAction("Change create by user Issue, by user "+userService.findUserById(dto.getChangeByUserId()).getName()+" .", dto.getChangeByUserId(), issue.getExternalId());
        }else {
            issue.setCreateBy(null);
        }
        if (dto.getAssignId() != 0) {
            issue.setAssign(userService.findUserById(dto.getAssignId()));
            actionService.addAction("Change assignee user Issue, by user "+userService.findUserById(dto.getChangeByUserId()).getName()+" .", dto.getChangeByUserId(), issue.getExternalId());
        }
        if (dto.getAssignGroupId() != 0) {
            issue.setAssignGroup(groupRepository.findById(dto.getAssignGroupId()).get());
            actionService.addAction("Change assignee group Issue, by user "+userService.findUserById(dto.getChangeByUserId()).getName()+" .", dto.getChangeByUserId(), issue.getExternalId());
        }
        if (dto.getVersion() != null)
            issue.setVersion(dto.getVersion());

        if (dto.getPlaningTimeCost() != 0){
            issue.setPlaningTimeCost(dto.getPlaningTimeCost());
        }

        //TODD Добавить проверку на изменение даты.
        issue.setLose(dto.isLose());
        issue.setStartDate(dto.getStartDate());
        issue.setEndDate(dto.getEndDate());
        issue.setLastChangeDate(LocalDateTime.now());

        issueRepository.save(issue);

        return true;

    }

    private void changeProject(Issue issue, long newProjectId, long changeByUserId){
        if (checkUserRole(changeByUserId, "MANAGER")){
                String oldProjectName = issue.getProject().getName();
                issue.setProject(projectService.findProjectByID(newProjectId));
                actionService.addAction("Change project Issue, old value ' "+oldProjectName+" ', new value ' "+projectService.findProjectByID(newProjectId).getName()+" ', change by user " + userService.findUserById(changeByUserId).getName() + " .", changeByUserId, issue.getExternalId());
            }else {
                throw new NotFoundRoleUserException("Not found role from user!");
            }
    }




    private void changePriority(Issue issue, int newPriority, long changeUserID){
        if (checkUserRole(changeUserID, "MANAGER")) {
            int oldPriority = issue.getPriority();
            issue.setPriority(newPriority);
            actionService.addAction("Change priority Issue, old value " + oldPriority + ", new value " + newPriority + " changed by user " + userService.findUserById(changeUserID).getName() + " .", changeUserID, issue.getExternalId());
        }else {
            throw new NotFoundRoleUserException("Not found role from user!");
        }
    }

    private boolean checkUserRole(long userId, String roleName){
        User changeUser = userService.findUserById(userId);
        for (UserRole role: changeUser.getRoleList()){
            if (role.getRoles().getName().equals(roleName) & role.getRoles().isWrite()) {
                return true;
            }

        }
        return false;
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
    public Issue findIssueInternalByExternalId(String extId) {
        return issueRepository.findByExternalId(extId);
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

        if (!parentIssue.isParent()) {
            parentIssue.setParent(true);
        }

        issueRepository.save(parentIssue);

        actionService.addAction("Add connection between parent issue "+parentId+" and child issue "+childIssue+" by user "+userService.findUserById(userId).getName()+" .", userId, parentIssue.getExternalId());

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

        actionService.addAction("Add comment by user "+userService.findUserById(dto.getUserId()).getName()+" .", dto.getUserId(), issue.getExternalId());

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

        actionService.addAction("Add response comment by user "+userService.findUserById(dto.getUserId()).getName()+" .", dto.getUserId(), issue.getExternalId());
    }

    @Override
    public boolean addTimeCost(TimeCostDto dto) {
        TimeCost timeCost = new TimeCost();
        Issue issue = issueRepository.findByExternalId(dto.getExternalId());
        IssueTimeCost issueTimeCost = new IssueTimeCost();

        switch (dto.getTime().charAt(dto.getTime().length() - 1)){
            case ('h'):
                timeCost.setHours(true);
                break;

            case ('d'):
                timeCost.setDay(true);
                break;

            case ('m'):
                timeCost.setMinutes(true);
                break;

            default:
                timeCost.setHours(true);
                break;
        }

        timeCost.setIssue(issue);
        timeCost.setCreateBy(userService.findUserById(dto.getCreateByUserId()));
        timeCost.setComment(dto.getComment());
        timeCost.setTime(Integer.parseInt(dto.getTime()));
        timeCost.setDateStart(dto.getDateStart());
        timeCost.setDateStop(dto.getDateStop());
        timeCost.setCreateDate(LocalDateTime.now());

        timeCostRepository.save(timeCost);

        issueTimeCost.setTimeCost(timeCost);
        issueTimeCost.setIssues(issue);
        issueTimeCost.setCreateDate(LocalDateTime.now());

        issueTimeCostRepository.save(issueTimeCost);

        int currentActualTimeCost = issue.getActualTimeCost();

        issue.setActualTimeCost(currentActualTimeCost + Integer.parseInt(dto.getTime()));

        issueRepository.save(issue);

        actionService.addAction("Add into issue time cost. ", dto.getCreateByUserId(), issue.getExternalId());

        return true;
    }

    @Override
    public boolean removeTimeCostFromIssue(String externalId, Long costId, Long changeUserId) {

        Issue issue = issueRepository.findByExternalId(externalId);

        for (IssueTimeCost tc: issue.getIssueTimeCosts()){
            if (tc.getTimeCost().getId() == costId){
                tc.setTimeCost(null);
                tc.setIssues(null);

                issueTimeCostRepository.save(tc);
            }
        }

        actionService.addAction("Remove time cost from issue.", changeUserId, issue.getExternalId());
        return true;
    }

    @Override
    public boolean addAttachmentInIssue(String externalId, Attachment attachment) {
        return false;
    }


    @Override
    public boolean removeAttachmentFromIssue(Long attachId) {
        return false;
    }

    @Override
    public List<Track> findIssueByProjectId(Long projectId) {

        List<Track> trackList = new ArrayList<>();

        for (Issue issue: issueRepository.findByProjectId(projectId))
            trackList.add(IssueToTrackTranslator.getTrack(issue));

        return trackList;
    }

    @Override
    public List<Track> findBindIssue(String externalId) {

        Issue issue = issueRepository.findByExternalId(externalId);

        List<Track> trackList = new ArrayList<>();

        for(IssueBind bi: issue.getIssuesListBind()){
            trackList.add(IssueToTrackTranslator.getTrack(bi.getIssues()));
        }

        return trackList;
    }

    @Override
    public List<Issue> findIssueByField(int priorityId, Long statusId, Long categoryId, String trackName, Long assignId, Long createById) {

        List<Issue> issueList = new ArrayList<>();

        issueList = issueRepository.findByTrackName(trackName);

        return null;
    }

    @Override
    public List<Track> findByBetweenDateStartEnd(LocalDateTime start, LocalDateTime end, Boolean StartEnd) {
        List<Track> tracks = new ArrayList<>();

        if (StartEnd){
            if (issueRepository.findAllByStartDateBetween(start, end).isEmpty())
                throw new NotFoundIssue("Issues not found!");

            for (Issue issue: issueRepository.findAllByStartDateBetween(start, end))
                tracks.add(IssueToTrackTranslator.getTrack(issue));

            return tracks;
        }else {
            if (issueRepository.findAllByEndDateBetween(start, end).isEmpty())
                throw new NotFoundIssue("Issues not found");

            for (Issue issue: issueRepository.findAllByEndDateBetween(start, end))
                tracks.add(IssueToTrackTranslator.getTrack(issue));

            return tracks;
        }

    }

    @Override
    public List<Track> findIssueByBetweenDate(LocalDateTime start, LocalDateTime end) {

        if (issueRepository.findAllByCreateDateBetween(start, end).isEmpty())
            throw new NotFoundIssue("Issues not found!");

        List<Track> tracks = new ArrayList<>();

        for (Issue issue: issueRepository.findAllByCreateDateBetween(start, end))
            tracks.add(IssueToTrackTranslator.getTrack(issue));

        return tracks;
    }

    @Override
    public boolean assignUser(String externalId, Long userId) {
        // Find Issue
        if (issueRepository.findByExternalId(externalId) == null) {
            throw new NotFoundIssue("Issue not found!");
        }
        //Find User
        if (userService.findUserById(userId) == null) {
            throw new NotFoundUserException("User not found!");
        }

        Issue issue = issueRepository.findByExternalId(externalId);
        User user = userService.findUserById(userId);

        issue.setAssign(user);
        issueRepository.save(issue);

        return true;
    }

    @Override
    public boolean assignGroup(String externalId, Long groupId) {

        if (issueRepository.findByExternalId(externalId) == null)
            throw new NotFoundIssue("Issue not found!");


        if (groupRepository.findById(groupId).isEmpty())
            throw new NotFoundGroupException("Group not found!");


        Issue issue = issueRepository.findByExternalId(externalId);
        Group group = groupRepository.findById(groupId).get();

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
