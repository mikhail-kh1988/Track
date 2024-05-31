package com.track.ui.controller;

import com.track.service.IGroupService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @GetMapping("/ui/users")
    public String viewPage(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allGroups", groupService.getAllGroup());
        return "users/users";
    }

}
