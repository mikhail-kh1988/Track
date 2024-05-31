package com.track.service;

import com.track.dto.ProjectDto;
import com.track.dto.StatusDto;
import com.track.entity.Category;
import com.track.entity.Project;
import com.track.entity.Status;
import com.track.entity.Track;

import java.util.List;

public interface IProjectService {

    void createNewProjectFromUI(Project project);
    Boolean createNewProject(String name);
    Boolean createNewTrack(String trackName, Long projectId);
    Boolean createNewStatus(StatusDto dto);
    Boolean createNewCategory(Long projectId, String categoryName);
    Boolean deleteTrackById(Long trackId);
    Boolean deleteStatusById(Long statusId);
    List<Track> getTracksByProject(Long projectId);
    List<Status> getStatusesByProject(Long projectId);
    List<Category> getCategoryByProject(Long projectId);
    Boolean deleteProjectById(Long id);
    Boolean changeNameProject(Long id, String newName);
    Boolean changeTypeProject(Long id, int newType);
    Boolean changeDescriptionProject(ProjectDto dto);
    List<Project> getAllProjects();
    Project findProjectByID(Long id);
    Project findProjectByName(String name);
    List<Project> findProjectsByType(int type);
    void addGroupInProject(Long groupId, Long projectId);
    void removeGroupFromProject(Long groupId, Long projectId);

    Boolean removeCategoryById(Long id);


}
