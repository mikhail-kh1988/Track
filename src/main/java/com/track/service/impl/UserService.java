package com.track.service.impl;

import com.track.dto.UserDto;
import com.track.entity.Role;
import com.track.entity.User;
import com.track.entity.UserRole;
import com.track.exception.NotFoundInternalUserException;
import com.track.repository.RoleRepository;
import com.track.repository.UserRepository;
import com.track.repository.UsersRolesRepository;
import com.track.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean createNewUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getFull_name());
        user.setJobTitle(dto.getJob_title());
        user.setLogin(dto.getLogin());
        user.setCreateDate(LocalDateTime.now());
        user.setPhoneNumber(dto.getPhone());
        user.setStatus(100);
        user.setEmail(dto.getEmail());

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
    public boolean addUserRole(String userLogin, String roleName, Long createByLoginId) {
        User createBy = userRepository.findById(createByLoginId).get();
        User user = userRepository.findUserByLogin(userLogin);
        Role role = roleRepository.findRoleByName(roleName);

        if (user != null & role != null & createBy != null){


            UserRole userRole = new UserRole();

            userRole.setUsers(user);
            userRole.setRoles(role);
            userRole.setCreateDate(LocalDateTime.now());
            userRole.setActive(true);
            userRole.setCreateBy(createBy);

            usersRolesRepository.save(userRole);

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(String userLogin) {
        User user = userRepository.findUserByLogin(userLogin);

        if (user != null) {

            userRepository.delete(user);

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRoleFromUser(String userLogin, String roleName) {

        User user = userRepository.findUserByLogin(userLogin);
        UserRole currentRole = new UserRole();

        for (UserRole ur: user.getRoleList()) {
            if (ur.getRoles().getName().equals(roleName)){
                currentRole.setId(ur.getId());

                usersRolesRepository.delete(currentRole);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean createNewRole(String nameRole) {

        if (roleRepository.findRoleByName(nameRole)==null) {

            Role role = new Role();

            role.setName(nameRole);
            role.setCreateDate(LocalDateTime.now());
            role.setRead(true);
            role.setWrite(true);

            roleRepository.save(role);

            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        for (User u: userRepository.findAll()) {
            list.add(u);
        }
        return list;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();

        for (Role r: roleRepository.findAll()) {
            roles.add(r);
        }

        return roles;
    }

    @Override
    public List<Role> getUserRoles(String userLogin) {

        List<Role> roles = new ArrayList<>();

        User user = userRepository.findUserByLogin(userLogin);

        for (UserRole tempRole: user.getRoleList()) {
            roles.add(tempRole.getRoles());
        }

        return roles;
    }

    @Override
    public User findUserById(long ownerId) {
        if (userRepository.findById(ownerId).isPresent())
            return userRepository.findById(ownerId).get();
        else throw new NotFoundInternalUserException("По указанному ID пользователь не найден.");
    }

    @Override
    public boolean setRoleRead(String roleName) {
        Role currentRole = roleRepository.findRoleByName(roleName);

        if (currentRole != null){

            currentRole.setWrite(false);
            currentRole.setRead(true);

            roleRepository.save(currentRole);

            return true;
        }
        return false;
    }

    @Override
    public boolean setRoleWrite(String roleName) {
        Role currentRole = roleRepository.findRoleByName(roleName);

        if (currentRole != null){

            currentRole.setRead(false);
            currentRole.setWrite(true);

            roleRepository.save(currentRole);

            return true;
        }
        return false;
    }
}
