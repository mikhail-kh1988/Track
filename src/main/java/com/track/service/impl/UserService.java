package com.track.service.impl;

import com.track.dto.UserDto;
import com.track.entity.Role;
import com.track.entity.User;
import com.track.repository.RoleRepository;
import com.track.repository.UserRepository;
import com.track.repository.UserRolesRepository;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean createNewUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getFull_name());
        user.setJobTitle(dto.getJob_title());
        user.setLogin(dto.getLogin());
        user.setCreateDate(LocalDateTime.now());

        User userSave = userRepository.save(user);

        if (userSave != null) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public boolean addUserRole(String userLogin, String roleName) {
        User user = userRepository.findUserByLogin(userLogin);
        Role role = roleRepository.findRoleByRoleName(roleName);

        return false;
    }

    @Override
    public boolean deleteUser(String userLogin) {
        return false;
    }

    @Override
    public boolean createNewRole(String nameRole) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
