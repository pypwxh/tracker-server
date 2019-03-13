package com.yfyld.tracker.mapper;

import com.yfyld.tracker.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ProjectUserRoleRelationMapper {
    int insertList(@Param("list") List<ProjectUserRoleRelationMapper> userRoleRelationList);
    List<Permission> getPermissionList(@Param("userId") Long userId,@Param("projectId") Long projectId);
}


