package com.track.controller;

import com.track.dto.ProjectDto;
import com.track.dto.StatusDto;
import com.track.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;


    @PutMapping("/{name}")
    public ResponseEntity<?> createNewProject(@PathVariable String name){
        return ResponseEntity.ok(projectService.createNewProject(name));
    }

    @PutMapping("/{projectId}/track/{name}")
    public ResponseEntity<?> createNewTrack(@PathVariable String name, Long projectId){
        return ResponseEntity.ok(projectService.createNewTrack(name, projectId));
    }
    @PutMapping("/status/create")
    public ResponseEntity<?> createNewStatus(@RequestBody StatusDto dto){
        return ResponseEntity.ok(projectService.createNewStatus(dto));
    }

    @PutMapping("/{projectId}/category/{name}")
    public ResponseEntity<?> createNewCategory(@PathVariable String name, Long projectId){
        return ResponseEntity.ok(projectService.createNewCategory(projectId, name));
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removeProject(@PathVariable long id){
        return ResponseEntity.ok(projectService.deleteProjectById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable long id){
        return ResponseEntity.ok(projectService.deleteTrackById(id));
    }

    @DeleteMapping("/status/{statusId}")
    public ResponseEntity<?> deleteStatusById(@PathVariable long statusId){
        return ResponseEntity.ok(projectService.deleteStatusById(statusId));
    }

    @GetMapping("/{projectId}/track/")
    public ResponseEntity<?> getTracksByProject(@PathVariable long projectId){
        return ResponseEntity.ok(projectService.getTracksByProject(projectId));
    }

    @GetMapping("/{projectId}/status/")
    public ResponseEntity<?>  getStatusesByProject(@PathVariable long projectId){
        return ResponseEntity.ok(projectService.getStatusesByProject(projectId));
    }

    @GetMapping("/{projectId}/category/")
    public ResponseEntity<?> getCategoryByProject(@PathVariable long projectId){
        return ResponseEntity.ok(projectService.getCategoryByProject(projectId));
    }

    @DeleteMapping("/category/{catId}")
    public ResponseEntity<?> removeCategoryById(@PathVariable long catId){
        return ResponseEntity.ok(projectService.removeCategoryById(catId));
    }

    @PutMapping("/{projectId}/{name}")
    public ResponseEntity<?> changeNameProject(@PathVariable long projectId, String name){
        return ResponseEntity.ok(projectService.changeNameProject(projectId, name));
    }

    @PostMapping("/description")
    public ResponseEntity<?> changeTypeProject(@RequestBody ProjectDto dto){
        return ResponseEntity.ok(projectService.changeDescriptionProject(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable long id){
        return ResponseEntity.ok(projectService.findProjectByID(id));
    }

    @PutMapping("/{projectId}/{groupId}")
    public ResponseEntity<?> addGroupInProject(@PathVariable long projectId, long groupId){
        projectService.addGroupInProject(groupId, projectId);
        return ResponseEntity.ok("{add group in project SUCCESS!}");
    }

    @DeleteMapping("/{projectId}/{groupId}")
    public ResponseEntity<?> removeGroupFromProject(@PathVariable long projectId, long groupId){
        projectService.removeGroupFromProject(groupId, projectId);
        return ResponseEntity.ok("{remove group from project SUCCESS!}");
    }




}
