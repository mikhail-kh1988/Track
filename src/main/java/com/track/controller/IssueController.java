package com.track.controller;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.TimeCostDto;
import com.track.dto.UpdateIssueDto;
import com.track.service.IIssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue")
public class IssueController {


    @Autowired
    private IIssueService issueService;

    @PutMapping("/")
    public ResponseEntity<?> createNewIssue(@RequestBody IssueDto dto){
        return ResponseEntity.ok(issueService.createNewIssue(dto));
    }

    @PostMapping("/upd")
    public ResponseEntity<?> updateIssue(@RequestBody UpdateIssueDto dto){
        return ResponseEntity.ok(issueService.changeIssue(dto));
    }

    @PutMapping("/comment")
    public ResponseEntity<?> addComment(@RequestBody CommentDto dto){
        issueService.addCommentInIssue(dto);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/comment/{commentId}/response")
    public ResponseEntity<?> addComment(@RequestBody CommentDto dto, @PathVariable long commentId){
        issueService.addResponseComment(dto, commentId);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{issue}")
    public ResponseEntity<?> getIssue(@PathVariable String issue){
        return ResponseEntity.ok(issueService.findIssueByExternalId(issue));
    }

    @GetMapping("/bind/{issue}")
    public ResponseEntity<?> getBindIssue(@PathVariable String issue){
        return ResponseEntity.ok(issueService.findBindIssue(issue));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getIssueByProject(@PathVariable long projectId){
        return ResponseEntity.ok(issueService.findIssueByProjectId(projectId));
    }

    @PutMapping("/assign/user/{issue}/{user}")
    public ResponseEntity<?> assignUserOnIssue(@PathVariable String issue, @PathVariable long user){
        return ResponseEntity.ok(issueService.assignUser(issue, user));
    }

    @PutMapping("/assign/group/{issue}/{group}")
    public ResponseEntity<?> assignGroupOnIssue(@PathVariable String issue, @PathVariable long group){
        return ResponseEntity.ok(issueService.assignGroup(issue, group));
    }

    @GetMapping("/{parent}/bind/{child}/{userId}")
    public ResponseEntity<?> bindIssue(@PathVariable String parent, @PathVariable String child, @PathVariable long userId){
        return ResponseEntity.ok(issueService.bindIssues(parent, child, userId));
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<?> deleteIssue(@PathVariable String issueId){
        return ResponseEntity.ok(issueService.deleteIssue(issueId));
    }

    @PostMapping("/addtimecost")
    public ResponseEntity<?> addTimeCost(@RequestBody TimeCostDto dto){
        return ResponseEntity.ok(issueService.addTimeCost(dto));
    }

}
