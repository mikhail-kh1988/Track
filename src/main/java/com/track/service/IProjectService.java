package com.track.service;

import com.track.entity.Project;
import java.util.List;

public interface IProjectService {

    Boolean createNewProject(String name);
    Boolean deleteProjectById(Long id);
    Boolean changeNameProject(Long id, String newName);
    Boolean changeTypeProject(Long id, int newType);
    Boolean changeDescriptionProject(Long id, String description);
    List<Project> getAllProjects();
    Project findProjectByID(Long id);
    Project findProjectByName(String name);
    List<Project> findProjectsByType(int type);



}
