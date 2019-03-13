package com.yfyld.tracker.controller;

import com.yfyld.tracker.util.CommonResult;
import com.yfyld.tracker.service.user.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "权限相关")
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @ApiOperation("权限列表查询")
    @GetMapping("/select-by-userId")
    public CommonResult selectListByUserId(@RequestParam("userId") Long userId){
        return new CommonResult().success(permissionService.selectListByUserId(userId));
    }
}
