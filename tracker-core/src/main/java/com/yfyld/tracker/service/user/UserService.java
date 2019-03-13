package com.yfyld.tracker.service.user;

import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.model.Role;
import com.yfyld.tracker.model.User;

import java.util.List;


public interface UserService {
    int addUser(User user);
    List<User> getUserList();
    User getUserByUsername(String username);
    List<Permission> getPermissionList(Long id);
    List<Role> getRoleList(Long userId);
    User getUserInfo(String username);
    String login(String username, String password, Boolean isRememberMe);
}
