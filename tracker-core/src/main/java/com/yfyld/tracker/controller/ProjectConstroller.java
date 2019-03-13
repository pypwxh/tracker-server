package com.yfyld.tracker.controller;

import com.yfyld.tracker.model.Project;
import com.yfyld.tracker.service.project.ProjectService;
import com.yfyld.tracker.util.CommonResult;

import com.yfyld.tracker.util.ValidateGroups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(description = "项目相关")
@RestController
@RequestMapping(value = "/project")
public class ProjectConstroller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectConstroller.class);

    @Autowired
    private ProjectService projectService;


    @PostMapping("/")
    @ApiOperation(value = "新增项目")
    public CommonResult addProject(@Validated(ValidateGroups.Default.class) @RequestBody Project project) {
        int count = projectService.addProject(project);
        if (count > 0) {
            return new CommonResult().success(null);
        } else {
            return new CommonResult().failed();
        }
    }

    @GetMapping("/")
    @ApiOperation(value = "项目列表")
    public CommonResult getProjectList() {
        return new CommonResult().pageSuccess(projectService.getProjectList());
    }


    @GetMapping("/{projectId}")
    @ApiOperation(value = "项目详情")
    public CommonResult getProjectInfo(@PathVariable(value="projectId",required=true) Long projectId) {
        return new CommonResult().success(projectService.getProjectInfo(projectId));
    }
}
