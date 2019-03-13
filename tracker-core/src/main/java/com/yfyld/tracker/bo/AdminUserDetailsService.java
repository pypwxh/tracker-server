package com.yfyld.tracker.bo;


import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.model.User;
import com.yfyld.tracker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        List<Permission>permissionList =userService.getPermissionList(user.getId());
        return new JwtUser(user,permissionList);
    }
}