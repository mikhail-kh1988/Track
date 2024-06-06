package com.track.ui.controller;


import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.TimeCostDto;
import com.track.dto.UpdateIssueDto;
import com.track.entity.issue.*;
import com.track.repository.IssueRepository;
import com.track.repository.TrackRepository;
import com.track.service.IIssueService;
import com.track.service.IProjectService;
import com.track.service.IUserService;
import com.track.tools.ChangeIssueScanner;
import com.track.ui.dto.AddCommentIssueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UIIssueController {

    @Autowired
    private IIssueService issueService;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private IUserService userService;


    @GetMapping("/ui/issue")
    private String viewPage(Model model){
        model.addAttribute("issues", issueService.findAllIssue());
        return "issue/issue";
    }

    @GetMapping("/ui/issue/{projectId}")
    private String viewIssueByProject(@PathVariable long projectId,  Model model){
        model.addAttribute("prjID", projectId);
        model.addAttribute("issueByProject", issueService.findIssueByProjectId(projectId));
        return "issue/issueByProject";
    }

    @PostMapping("/ui/issue/update")
    private String updateIssue(@ModelAttribute("issue") Issue issue){

        Long id = issueRepository.findByExternalId(issue.getExternalId()).getId();

        issue.setId(id);

        issueRepository.save(issue);

        return "redirect:/ui/issue/"+issue.getProject().getId();
    }

    @GetMapping("/ui/issue/change/{extId}")
    private String changeIssue(@PathVariable String extId, Model model){

        Issue issue = issueService.findIssueInternalByExternalId(extId);

        List<Comment> commentList = new ArrayList<>();
        List<Action> actionList = new ArrayList<>();
        List<TimeCost> timeCostList = new ArrayList<>();

        for (IssueComment ic : issue.getIssueComment())
            commentList.add(ic.getComments());

        for (IssueAction ia : issue.getIssueActions())
            actionList.add(ia.getAction());

        for (IssueTimeCost itc :issue.getIssueTimeCosts())
            timeCostList.add(itc.getTimeCost());

        AddCommentIssueDto addCommentIssueDto = new AddCommentIssueDto();
        addCommentIssueDto.setExternalIdIssue(issue.getExternalId());

        TimeCostDto timeCostDto = new TimeCostDto();
        timeCostDto.setExternalId(issue.getExternalId());

        model.addAttribute("prjID", issue.getProject().getId());
        model.addAttribute("addComments", addCommentIssueDto);
        model.addAttribute("addTimeCost", timeCostDto);
        model.addAttribute("issue", issue);
        model.addAttribute("comments", commentList);
        model.addAttribute("actions", actionList);
        model.addAttribute("timeCosts", timeCostList);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("tracks", projectService.getTracksByProject(issue.getProject().getId()));
        model.addAttribute("statuses", projectService.getStatusesByProject(issue.getProject().getId()));
        model.addAttribute("categories", projectService.getCategoryByProject(issue.getProject().getId()));

        return "issue/change_issue";
    }

    @PostMapping("/ui/issue/addComment")
    private String addComment(@ModelAttribute("addComments") AddCommentIssueDto comment){

        CommentDto commentDto = new CommentDto();

        commentDto.setCommentBody(comment.getBody());
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setExternalIdIssue(comment.getExternalIdIssue());

        issueService.addCommentInIssue(commentDto);


        return "redirect:/ui/issue/change/"+comment.getExternalIdIssue();
    }

    @PostMapping("/ui/issue/addTimeCost")
    private String addTimeCost(@ModelAttribute("addTimeCost") TimeCostDto timeCostDto){

        issueService.addTimeCost(timeCostDto);

        return "redirect:/ui/issue/change/"+timeCostDto.getExternalId();
    }

    @GetMapping("/ui/issue/create")
    private String viewCreateNewIssue(Model model){

        IssueDto issueDto = new IssueDto();

        model.addAttribute("issueDto", issueDto);
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("tracks", trackRepository.findAll());
        model.addAttribute("users", userService.getAllUsers());

        return "issue/createIssue";
    }

    @PostMapping("/ui/issue/createIssue")
    private String createNewIssue(@ModelAttribute IssueDto issueDto){

        issueService.createNewIssue(issueDto);

        return "redirect:/ui/issue/"+issueDto.getProjectId();
    }

    @GetMapping("/ui/issue/createByProject/{id}")
    private String viewCreateNewIssueByProject(@PathVariable long id,  Model model){
        IssueDto issueDto = new IssueDto();

        model.addAttribute("issueDto", issueDto);
        model.addAttribute("projects", projectService.findProjectByID(id));
        model.addAttribute("tracks", projectService.getTracksByProject(id));
        model.addAttribute("statuses", projectService.getStatusesByProject(id));
        model.addAttribute("categories", projectService.getCategoryByProject(id));
        model.addAttribute("users", userService.getAllUsers());

        return "issue/createIssue";
    }

}
