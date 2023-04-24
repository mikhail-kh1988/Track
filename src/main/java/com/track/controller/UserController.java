package com.track.controller;

import com.track.dto.UserDto;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/")
    public ResponseEntity<?> addNewUser(@RequestBody UserDto dto){
        return ResponseEntity.ok(userService.createNewUser(dto));
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<?> deleteUserByLogin(@PathVariable String login){
        return ResponseEntity.ok(userService.deleteUser(login));
    }


    @GetMapping("/{login}")
    public ResponseEntity<?> getUserByLogin(@PathVariable String login){
        return ResponseEntity.ok(userService.findUserByLogin(login));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/role/{roleName}")
    public ResponseEntity<?> createNewRole(@PathVariable String roleName){
        return ResponseEntity.ok(userService.createNewRole(roleName));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(userService.getAllRoles());
    }

    @GetMapping("/{login}/roles")
    public ResponseEntity<?> getRolesByUser(@PathVariable String login){
        return ResponseEntity.ok(userService.getUserRoles(login));
    }

    @PutMapping("/{login}/{role}/{createBy}")
    public ResponseEntity<?> addRoleUser(@PathVariable String login, @PathVariable String role, @PathVariable long createBy){
        return ResponseEntity.ok(userService.addUserRole(login, role, createBy));
    }

    @DeleteMapping("/{login}/{role}")
    public ResponseEntity<?> deleteRoleUser(@PathVariable String login, @PathVariable String role){
        return ResponseEntity.ok(userService.deleteRoleFromUser(login, role));
    }

    @GetMapping("/roles/{role}/{doing}")
    public ResponseEntity<?> readWriteRole(@PathVariable String role, @PathVariable String doing){
        if (doing.equals("read")){
            return ResponseEntity.ok(userService.setRoleRead(role));
        } else if (doing.equals("write")){
            return ResponseEntity.ok(userService.setRoleWrite(role));
        }

        return ResponseEntity.ok("Ups!");
    }
}
