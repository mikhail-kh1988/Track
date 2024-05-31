package com.track.ui.controller;

import com.track.dto.ProjectDto;
import com.track.dto.StatusDto;
import com.track.entity.Category;
import com.track.entity.Project;
import com.track.entity.Status;
import com.track.entity.Track;
import com.track.repository.TrackRepository;
import com.track.service.IGroupService;
import com.track.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UIProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping("/ui/project")
    public String viewAllProject(Model model){
        model.addAttribute("allProject", projectService.getAllProjects());
        return "project/project";
    }

    @GetMapping("/ui/project/add")
    public String addNewProject(Model model){
        Project project = new Project();
        model.addAttribute("new_project", project);
        return "project/add_project";
    }

    @PostMapping("/create_project")
    public String saveProject(@ModelAttribute("new_project") Project project){
        projectService.createNewProjectFromUI(project);
        return "redirect:/ui/project";
    }

    @PostMapping("/ui/project/save")
    public String changeProject(@ModelAttribute("project") Project project){
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setPrefix(project.getPrefix());
        dto.setDescription(project.getDescription());
        projectService.changeDescriptionProject(dto);
        return "redirect:/ui/project";
    }

    @GetMapping("/ui/project/change/{id}")
    public String changeProject(@PathVariable(value = "id") long id, Model model){
        Project project = projectService.findProjectByID(id);
        model.addAttribute("project", project);
        model.addAttribute("track", projectService.getTracksByProject(id));
        model.addAttribute("status", projectService.getStatusesByProject(id));
        model.addAttribute("category", projectService.getCategoryByProject(id));
        model.addAttribute("group", projectService.findProjectByID(id).getGroups());
        return "project/change_project";
    }

    @PostMapping("/create_track")
    public String createTrack(@ModelAttribute("new_track") Track track){
        projectService.createNewTrack(track.getName(), track.getProject().getId());
        return "redirect:/ui/project";
    }

    @GetMapping("/ui/project/track")
    public String createNewCreateTrack(Model model){
        Track track = new Track();
        model.addAttribute("new_track", track);
        model.addAttribute("AllProject", projectService.getAllProjects());
        return "project/add_track";
    }

    @PostMapping("/create_category")
    public String createCategory(@ModelAttribute("new_category") Category category){
        projectService.createNewCategory(category.getProject().getId(), category.getName());
        return "redirect:/ui/project";
    }

    @GetMapping("/ui/project/category")
    public String createNewCategory(Model model){
        Track track = new Track();
        model.addAttribute("new_category", track);
        model.addAttribute("AllProject", projectService.getAllProjects());
        return "project/add_category";
    }

    @PostMapping("/create_status")
    public String createStatus(@ModelAttribute("new_status") Status status){
        StatusDto dto = new StatusDto();
        dto.setName(status.getName());
        dto.setProject_id(status.getProject().getId());
        dto.setTrack_id(status.getTrack().getId());
        dto.setClosed(status.isClosed());
        dto.setOrder(status.getOrders());
        projectService.createNewStatus(dto);
        return "redirect:/ui/project";
    }

    @GetMapping("/ui/project/status")
    public String createNewStatus(Model model){
        Status status = new Status();
        model.addAttribute("new_status", status);
        model.addAttribute("AllProject", projectService.getAllProjects());
        model.addAttribute("AllGroups", groupService.getAllGroup());
        model.addAttribute("AllTrack", trackRepository.findAll());
        return "project/add_status";
    }
}
