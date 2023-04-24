package com.track.controller;

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

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removeProject(@PathVariable long id){
        return ResponseEntity.ok(projectService.deleteProjectById(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }



}
