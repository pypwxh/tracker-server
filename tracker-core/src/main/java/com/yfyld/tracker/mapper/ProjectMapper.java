package com.yfyld.tracker.mapper;

import com.yfyld.tracker.model.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int addProject(Project project);
    List<Project> getProjectList();
    Project getProjectInfo(Long projectId);
}
