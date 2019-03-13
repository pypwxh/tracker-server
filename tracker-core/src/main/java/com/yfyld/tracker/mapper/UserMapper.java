package com.yfyld.tracker.mapper;

import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    int insert(User user);
    List<User> getUserList();
    User getUserByUsername(String userName);
    List<Permission> getPermissionList(Long id);
    User getUserInfo(String username);
}
