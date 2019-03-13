package com.yfyld.tracker.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.yfyld.tracker.mapper.UserRoleRelationMapper;
import com.yfyld.tracker.service.user.UserService;
import com.yfyld.tracker.mapper.UserMapper;
import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.model.Role;
import com.yfyld.tracker.model.User;
import com.yfyld.tracker.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private Role admin = new Role("ADMIN", "管理员");
    private Role developer = new Role("DEVELOPER", "开发者");

    {
        Permission p1 = new Permission();
        p1.setValue("用户管理");
    }


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> getUserList() {
        PageHelper.startPage(1, 2);
        return userMapper.getUserList();
    }

    @Override
    public User getUserInfo(String username){
        return userMapper.getUserInfo(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public List<Permission> getPermissionList(Long userId) {
        return userRoleRelationMapper.getPermissionList(userId);
    }

    @Override
    public List<Role> getRoleList(Long userId) {
        return userRoleRelationMapper.getRoleList(userId);
    }


    @Override
    public String login(String username, String password,Boolean isRememberMe) {
        String token = null;
        String role = null;


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
            );
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);


//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                role += authority.getAuthority();
//            }

            Claims claims = Jwts.claims();
            claims.put("role", authentication.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));

            token = JwtTokenUtil.TOKEN_PREFIX + JwtTokenUtil.createToken(claims,username, isRememberMe);
            return token;
        } catch (AuthenticationException e) {
            return token;
        }
    }

}
