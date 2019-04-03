package com.yfyld.tracker.service.project;

import com.yfyld.tracker.model.Project;

import java.util.List;

public interface ProjectService {
    int addProject(Project project);
    List<Project> getProjectList(String username,String role);
    Project getProjectInfo(Long projectId);
}
