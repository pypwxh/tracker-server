package com.yfyld.tracker.service.user;

import com.yfyld.tracker.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> selectListByUserId(Long userId);
}
