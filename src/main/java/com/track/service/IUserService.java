package com.track.service;

import com.track.dto.UserDto;
import com.track.entity.Role;
import com.track.entity.User;
import java.util.List;

public interface IUserService {

    boolean createNewUser(UserDto dto);
    User findUserByLogin(String login);
    boolean addUserRole(String userLogin, String role, Long createByLoginId);
    boolean deleteUser(String userLogin);
    boolean deleteRoleFromUser(String userLogin, String roleName);

    List<User> getAllUsers();
    List<Role> getUserRoles(String userLogin);

    User findUserById(long userId);

    boolean createNewRole(String nameRole);

    List<Role> getAllRoles();

    boolean setRoleRead(String roleName);

    boolean setRoleWrite(String roleName);
}
