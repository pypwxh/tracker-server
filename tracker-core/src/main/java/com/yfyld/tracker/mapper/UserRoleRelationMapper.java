package com.yfyld.tracker.mapper;

import com.yfyld.tracker.model.Permission;
import com.yfyld.tracker.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserRoleRelationMapper {
    int insertList(@Param("list") List<UserRoleRelationMapper> userRoleRelationList);
    List<Permission> getPermissionList(@Param("userId") Long userId);
    List<Role> getRoleList(@Param("userId") Long userId);
}


