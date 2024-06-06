package com.track.ui.controller;

import com.track.entity.Group;
import com.track.entity.User;
import com.track.repository.GroupRepository;
import com.track.repository.RoleRepository;
import com.track.repository.UserRepository;
import com.track.service.IGroupService;
import com.track.service.IUserService;
import com.track.ui.dto.AddUserInGroupDtoUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class UIUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/ui/users")
    public String viewPage(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allGroups", groupService.getAllGroup());
        return "users/users";
    }

    @GetMapping("/ui/users/new_group")
    public String viewPageCreateGroup(Model model){
        Group group = new Group();
        model.addAttribute("group", group);
        model.addAttribute("createByUsers", userService.getAllUsers());
        return "users/new_group";
    }


    @PostMapping("/ui/users/create_group")
    public String createNewGroup(@ModelAttribute Group group){

        group.setCreateDate(LocalDateTime.now());
        groupRepository.save(group);

        return "redirect:/ui/users/";
    }

    @GetMapping("/ui/users/view_group/{groupId}")
    public String viewAndChangeGroup(@PathVariable long groupId, Model model){

        AddUserInGroupDtoUI dtoUI = new AddUserInGroupDtoUI();

        model.addAttribute("dtoUI", dtoUI);
        model.addAttribute("allUsersGroup", groupService.getAllUsersFromGroupById(groupId));
        model.addAttribute("group", groupRepository.findById(groupId).get());
        model.addAttribute("addUsers", userService.getAllUsers());

        return "users/view_group";
    }

    @PostMapping("/ui/users/add_user_group")
    public String addUserInGroup(@ModelAttribute AddUserInGroupDtoUI dtoUI){

        groupService.addUserInGroup(dtoUI.getUserId(), dtoUI.getGroupId(), 1l);

        return "redirect:/ui/users/view_group/"+dtoUI.getGroupId();
    }

    @GetMapping("/ui/users/newUser")
    public String viewPageCreateNewUser(Model model){

        User user = new User();
        model.addAttribute("user", user);

        return "users/newUser";

    }

    @PostMapping("/ui/users/createUser")
    public String createNewUser(@ModelAttribute User user){

        user.setCreateDate(LocalDateTime.now());
        //user.setCreateBy(userRepository.findById(1L).get());

        userRepository.save(user);

        return "redirect:/ui/users/";
    }

    @GetMapping("/ui/users/change/{id}")
    public String changeUser(@PathVariable long id, Model model){

        User user = userService.findUserById(id);

        model.addAttribute("user", user);

        return "users/viewUser";
    }

    @PostMapping("/ui/users/updateUser")
    public String updateUser(@ModelAttribute User user){

        Long id = userService.findUserByLogin(user.getLogin()).getId();

        user.setId(id);

        userRepository.save(user);

        return "redirect:/ui/users";
    }




}
