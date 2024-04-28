package com.track.controller;

import com.track.dto.ChangeDateSprintDto;
import com.track.dto.SprintDto;
import com.track.service.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private ISprintService sprintService;


    @PostMapping ("/")
    public ResponseEntity<?> createSprint(@RequestBody SprintDto dto){
        sprintService.createNewSprint(dto);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable long id){
        return ResponseEntity.ok(sprintService.deleteSprintById(id));
    }

    @PutMapping("/add/{issueId}/{sprintId}")
    public ResponseEntity<?> addIssueInSprint(@PathVariable String issueId, @PathVariable long sprintId){
        return ResponseEntity.ok(sprintService.addIssueInSprint(issueId, sprintId));

    }

    @DeleteMapping("/delete/{issueId}/{sprintId}")
    public ResponseEntity<?> deleteIssueFromSprint(@PathVariable String issueId, @PathVariable long sprintId){
        return ResponseEntity.ok(sprintService.deleteIssueFromSprint(issueId, sprintId));
    }

    @PostMapping("/change/{sprintId}")
    public ResponseEntity<?> changeDateIntoSprint(@PathVariable long sprintId, @RequestBody ChangeDateSprintDto dto){
        return ResponseEntity.ok(sprintService.changeDateIntoSprint(sprintId, dto));
    }

    @GetMapping("/{sprintId}")
    public ResponseEntity<?> getIssueFromSprint(@PathVariable long sprintId){
        return ResponseEntity.ok(sprintService.getIssuesFromSprintById(sprintId));
    }

    @GetMapping("/{sprintName}/byName")
    public ResponseEntity<?> getIssueByNameSprint(@PathVariable String sprintName){
        return ResponseEntity.ok(sprintService.getIssuesFromSprintByName(sprintName));
    }

}
