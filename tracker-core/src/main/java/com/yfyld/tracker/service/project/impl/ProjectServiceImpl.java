package com.yfyld.tracker.service.project.impl;

import com.github.pagehelper.PageHelper;
import com.yfyld.tracker.mapper.ProjectMapper;
import com.yfyld.tracker.model.Project;
import com.yfyld.tracker.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ProjectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public int addProject(Project project) {
        return projectMapper.addProject(project);
    }

    @Override
    public List<Project> getProjectList(String username,String role) {
        PageHelper.startPage(1, 20);
        return projectMapper.getProjectList(username,role);
    }

    @Override
    public Project getProjectInfo(Long projectId) {
        return projectMapper.getProjectInfo(projectId);
    }
}
