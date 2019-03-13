package com.yfyld.tracker.service.user.impl;

import com.yfyld.tracker.mapper.PermissionMapper;
import com.yfyld.tracker.service.user.PermissionService;
import com.yfyld.tracker.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectListByUserId(Long userId){
        return permissionMapper.selectListByUserId(userId);
    }
}
