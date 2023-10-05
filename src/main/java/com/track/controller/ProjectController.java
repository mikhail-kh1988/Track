package com.track.controller;

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



}
