package com.yfyld.tracker.mapper;

import com.yfyld.tracker.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface PermissionMapper {
    List<Permission> selectListByUserId(@Param("userId") Long userId);
}
