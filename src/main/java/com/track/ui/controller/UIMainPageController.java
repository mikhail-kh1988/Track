package com.track.ui.controller;

import com.track.service.IGroupService;
import com.track.service.IProjectService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UIMainPageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IProjectService projectService;


    @GetMapping("/ui/main")
    public String viewPage(Model model){
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("groups", groupService.getAllGroup());
        return "main";
    }



}
