package com.track.controller;

import com.track.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @PutMapping("/{groupName}/{createById}")
    public ResponseEntity<?> createNewGroup(@PathVariable String groupName, @PathVariable Long createById){
        return ResponseEntity.ok(groupService.createNewGroup(groupName, createById));
    }

    @DeleteMapping("/{groupID}")
    public ResponseEntity<?> removeGroup(@PathVariable Long groupID){
        return ResponseEntity.ok(groupService.removeGroup(groupID));
    }

    @PutMapping("/owner/{userID}/{groupID}")
    public ResponseEntity<?> addOwnerGroup(@PathVariable Long userID, @PathVariable Long groupID){
        return ResponseEntity.ok(groupService.addOwnerGroup(userID, groupID));
    }

    @PutMapping("/deactivate/{groupID}")
    public ResponseEntity<?> deactivateGroup(@PathVariable Long groupID){
        return ResponseEntity.ok(groupService.deactivateGroup(groupID));
    }

    @PutMapping("/user/{userID}/{groupID}/{createByID}")
    public ResponseEntity<?> addUserInGroup(@PathVariable Long userID, @PathVariable Long groupID, @PathVariable Long createByID){
        return ResponseEntity.ok(groupService.addUserInGroup(userID,groupID,createByID));
    }

    @DeleteMapping("/user/{userID}/{groupID}")
    public ResponseEntity<?> removeUserFromGroup(@PathVariable Long userID, @PathVariable Long groupID){
        return ResponseEntity.ok(groupService.removeUserFromGroup(userID, groupID));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllGroup(){
        return ResponseEntity.ok(groupService.getAllGroup());
    }

    @GetMapping("/name/{groupName}")
    public ResponseEntity<?> getUsersFromGroupByName(@PathVariable String groupName){
        return ResponseEntity.ok(groupService.getAllUsersFromGroupByName(groupName));
    }

    @GetMapping("/id/{ID}")
    public ResponseEntity<?> getUsersFromGroupByID(@PathVariable Long ID){
        return ResponseEntity.ok(groupService.getAllUsersFromGroupById(ID));
    }

}
