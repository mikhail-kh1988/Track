package com.track.controller;

import com.track.dto.CommentDto;
import com.track.dto.IssueDto;
import com.track.dto.UpdateIssueDto;
import com.track.service.IIssueService;
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

    @GetMapping("/{issue}")
    public ResponseEntity<?> getIssue(@PathVariable String issue){
        return ResponseEntity.ok(issueService.findIssueByExternalId(issue));
    }

    @GetMapping("/{parent}/bind/{child}/{userId}")
    public ResponseEntity<?> bindIssue(@PathVariable String parent, @PathVariable String child, @PathVariable long userId){
        return ResponseEntity.ok(issueService.bindIssues(parent, child, userId));
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<?> deleteIssue(@PathVariable String issueId){
        return ResponseEntity.ok(issueService.deleteIssue(issueId));
    }

}
