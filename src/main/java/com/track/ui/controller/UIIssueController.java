package com.track.ui.controller;


import com.track.dto.UpdateIssueDto;
import com.track.entity.issue.*;
import com.track.repository.IssueRepository;
import com.track.service.IIssueService;
import com.track.service.IProjectService;
import com.track.service.IUserService;
import com.track.tools.ChangeIssueScanner;
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
    private IUserService userService;


    @GetMapping("/ui/issue")
    private String viewPage(Model model){
        model.addAttribute("issues", null);
        return "issue/issue";
    }

    @GetMapping("/ui/issue/{projectId}")
    private String viewIssueByProject(@PathVariable long projectId,  Model model){
        model.addAttribute("issueByProject", issueService.findIssueByProjectId(projectId));
        return "issue/issue";
    }

    @PostMapping("/ui/issue/update")
    private String updateIssue(@ModelAttribute("issue") Issue issue){

        Long id = issueRepository.findByExternalId(issue.getExternalId()).getId();

        issue.setId(id);

        issueRepository.save(issue);

        return "redirect:/ui/issue/1";
    }

    @GetMapping("/ui/issue/change/{extId}")
    private String changeIssue(@PathVariable String extId, Model model){

        Issue issue = issueService.findIssueInternalByExternalId(extId);

        List<Comment> commentList = new ArrayList<>();
        List<Action> actionList = new ArrayList<>();

        for (IssueComment ic : issue.getIssueComment())
            commentList.add(ic.getComments());

        for (IssueAction ia : issue.getIssueActions())
            actionList.add(ia.getAction());

        model.addAttribute("issue", issue);
        model.addAttribute("comments", commentList);
        model.addAttribute("actions", actionList);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("tracks", projectService.getTracksByProject(issue.getProject().getId()));
        model.addAttribute("statuses", projectService.getStatusesByProject(issue.getProject().getId()));
        model.addAttribute("categories", projectService.getCategoryByProject(issue.getProject().getId()));

        return "issue/change_issue";
    }

}
