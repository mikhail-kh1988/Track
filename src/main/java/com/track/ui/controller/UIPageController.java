package com.track.ui.controller;

import com.track.entity.Project;
import com.track.entity.issue.Issue;
import com.track.entity.pages.Page;
import com.track.service.IPageService;
import com.track.service.IProjectService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class UIPageController {

    @Autowired
    private IPageService pageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;


    @GetMapping("/ui/pages")
    public String viewPage(Model model){
        model.addAttribute("pages", pageService.getAllPages());
        return "pages/pages";
    }

    @GetMapping("/ui/pages/by_project/{projectId}")
    public String viewPageByProject(@PathVariable long projectId, Model model){
        model.addAttribute("pages", pageService.findByProjectId(projectId));
        return "pages_project";
    }

    @GetMapping("/ui/pages/new")
    public String viewNewPageCreate(Model model){
        Page page = new Page();
        model.addAttribute("new_page", page);
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("admins", userService.getAllUsers());
        return "pages/add_page";
    }

    @PostMapping("/ui/pages/newpage")
    public String addNewPage(@ModelAttribute("new_page")Page page){
        page.setCreateDate(LocalDateTime.now());
        pageService.createNewPage(page);

        return "redirect:/ui/pages/";
    }

    @GetMapping("/ui/pages/view/{pageId}")
    public String getViewPage(@PathVariable long pageId, Model model){
        Page page = pageService.findByIdPage(pageId);
        model.addAttribute("page", page);
        return "pages/view_page";
    }

    @GetMapping("/ui/pages/stared/{pageId}")
    public String setStar(@PathVariable long pageId, Model model){
        Page page = pageService.findByIdPage(pageId);
        pageService.setStar(pageId);
        model.addAttribute("page", page);
        return "redirect:/ui/pages/";
    }
}
