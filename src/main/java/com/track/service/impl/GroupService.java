package com.track.service.impl;

import com.track.dto.GroupDto;
import com.track.entity.Group;
import com.track.entity.User;
import com.track.entity.UserGroup;
import com.track.repository.GroupRepository;
import com.track.repository.UserGroupRepository;
import com.track.repository.UserRepository;
import com.track.service.IGroupService;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public boolean createNewGroup(String groupName, Long createByID) {
        Group group = groupRepository.findGroupByName(groupName);
        User user = userService.findUserById(createByID);

        if (group.getName().equals(groupName)){
            return false;
        }else if (user != null){

            Group newGroup = new Group();
            newGroup.setName(groupName);
            newGroup.setCreateDate(LocalDateTime.now());
            newGroup.setActive(true);
            newGroup.setStatus(100);
            newGroup.setCreateBy(user);
        }
        return false;
    }

    @Override
    public boolean addOwnerGroup(Long userID, Long groupID) {

        User user = userService.findUserById(userID);
        Group group = groupRepository.findById(groupID).get();

        if (user != null & group != null){

            group.setOwner(user);

            groupRepository.save(group);

            return true;
        }

        return false;
    }

    @Override
    public boolean deactivateGroup(Long id) {
        Group group = groupRepository.findById(id).get();

        if (group != null){

            group.setActive(false);

            groupRepository.save(group);
            return true;
        }

        return false;
    }

    @Override
    public boolean addUserInGroup(Long userID, Long groupID, Long createByUserID) {

        User user = userService.findUserById(userID);
        Group group = groupRepository.findById(groupID).get();

        if (user != null & group != null){

            User createBy = userService.findUserById(createByUserID);

            UserGroup userGroup = new UserGroup();
            userGroup.setUser(user);
            userGroup.setGroup(group);
            userGroup.setCreateDate(LocalDateTime.now());
            userGroup.setCreateBy(createBy);

            userGroupRepository.save(userGroup);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeUserFromGroup(Long userID, Long groupID) {

        Group group = groupRepository.findById(groupID).get();
        User user = userService.findUserById(userID);

        if (group != null & user != null){

            UserGroup removeUserFromGroup = new UserGroup();

            for (UserGroup userGroup: group.getGroupList()) {
                if (userGroup.getUser().getId() == user.getId()){
                    removeUserFromGroup.setId(userGroup.getId());
                }
            }

            userGroupRepository.delete(removeUserFromGroup);

            return true;
        }

        return false;
    }

    @Override
    public List<Group> getAllGroup() {

        List<Group> groupList = new ArrayList<>();

        for (Group g: groupRepository.findAll()) {
            groupList.add(g);
        }

        return groupList;
    }

    @Override
    public List<User> getAllUsersFromGroupByName(String groupName) {
        List<User> userList = new ArrayList<>();

        Group group = groupRepository.findGroupByName(groupName);

        for (UserGroup ug: group.getGroupList()) {
            userList.add(ug.getUser());
        }

        return userList;
    }

    @Override
    public List<User> getAllUsersFromGroupById(Long groupID) {
        List<User> userList = new ArrayList<>();

        Group group = groupRepository.findById(groupID).get();

        for (UserGroup ug: group.getGroupList()) {
            userList.add(ug.getUser());
        }

        return userList;
    }
}
