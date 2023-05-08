package com.track.service;

import com.track.dto.StatusDto;
import com.track.entity.Project;
import com.track.entity.Status;
import com.track.entity.Track;

import java.util.List;

public interface IProjectService {

    Boolean createNewProject(String name);
    Boolean createNewTrack(String trackName, Long projectId);
    Boolean createNewStatus(StatusDto dto);
    Boolean deleteTrackById(Long trackId);
    Boolean deleteStatusById(Long statusId);
    List<Track> getTracksByProject(Long projectId);
    List<Status> getStatusesByProject(Long projectId);
    Boolean deleteProjectById(Long id);
    Boolean changeNameProject(Long id, String newName);
    Boolean changeTypeProject(Long id, int newType);
    Boolean changeDescriptionProject(Long id, String description);
    List<Project> getAllProjects();
    Project findProjectByID(Long id);
    Project findProjectByName(String name);
    List<Project> findProjectsByType(int type);
    void addGroupInProject(Long groupId, Long projectId);
    void removeGroupFromProject(Long groupId, Long projectId);
    Boolean createNewCategory(String categoryName, Long projectId);
    Boolean removeCategoryById(Long id);


}
