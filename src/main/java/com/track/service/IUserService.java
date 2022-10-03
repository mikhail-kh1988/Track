package com.track.service;

import com.track.dto.UserDto;
import com.track.entity.User;
import java.util.List;

public interface IUserService {

    boolean createNewUser(UserDto dto);
    User findUserByLogin(String login);
    boolean addUserRole(String userLogin, String role);
    boolean deleteUser(String userLogin);

    boolean createNewRole(String nameRole);
    List<User> getAllUsers();
}
