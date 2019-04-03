package com.yfyld.tracker.mapper;

import com.yfyld.tracker.model.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int addProject(Project project);
    List<Project> getProjectList(@Param("username") String username,@Param("role") String role);
    Project getProjectInfo(Long projectId);
}
