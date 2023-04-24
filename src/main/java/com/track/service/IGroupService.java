package com.track.service;

import com.track.entity.Group;
import com.track.entity.User;
import java.util.List;

public interface IGroupService {

    boolean createNewGroup(String groupName, Long createByID);
    boolean removeGroup(Long groupId);
    boolean addOwnerGroup(Long userID, Long groupID);
    boolean deactivateGroup(Long id);
    boolean addUserInGroup(Long userID, Long groupID, Long createByUserID);
    boolean removeUserFromGroup(Long userID, Long groupID);
    List<Group> getAllGroup();
    List<User> getAllUsersFromGroupByName(String groupName);
    List<User> getAllUsersFromGroupById(Long groupID);
}
