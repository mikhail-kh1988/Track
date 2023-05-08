package com.track.service.impl;

import com.track.dto.StatusDto;
import com.track.entity.*;
import com.track.repository.*;
import com.track.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Boolean createNewProject(String name) {

        Project project = projectRepository.findByName(name);

        if (project == null){

            project = new Project();

            project.setName(name);
            project.setCreateDate(LocalDateTime.now());
            project.setType(100);

            projectRepository.save(project);

            return true;
        }

        return false;
    }

    @Override
    public Boolean createNewTrack(String trackName, Long projectId) {
        Project project = projectRepository.findById(projectId).get();

        if (!trackName.equals(" ")) {

            Track track = new Track();
            track.setName(trackName);
            track.setProject(project);

            trackRepository.save(track);
            return true;
        }

        return false;
    }

    @Override
    public Boolean createNewStatus(StatusDto dto) {

        Project project = projectRepository.findById(dto.getProject_id()).get();

        if (!dto.getName().equals(" ")){

            Status status = new Status();
            status.setProject(project);
            status.setName(dto.getName());
            status.setClosed(dto.isClosed());
            status.setOrders(dto.getOrder());

            statusRepository.save(status);
            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteTrackById(Long trackId) {

        Track track = trackRepository.findById(trackId).get();

        if (track != null){

            trackRepository.delete(track);

            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteStatusById(Long statusId) {

        Status status = statusRepository.findById(statusId).get();

        if (status != null){

            statusRepository.delete(status);

            return true;
        }

        return false;
    }

    @Override
    public List<Track> getTracksByProject(Long projectId) {

        List<Track> tracks = new ArrayList<>();

        for (Track tr: trackRepository.findByProjectId(projectId)) {
            tracks.add(tr);
        }

        return tracks;
    }

    @Override
    public List<Status> getStatusesByProject(Long projectId) {

        List<Status> statuses = new ArrayList<>();

        for (Status st: statusRepository.findByProjectId(projectId)) {
            statuses.add(st);
        }

        return statuses;
    }

    @Override
    public Boolean deleteProjectById(Long id) {

        Project project = projectRepository.findById(id).get();

        if (project != null){

            projectRepository.delete(project);

            return true;
        }
        return false;
    }

    @Override
    public Boolean changeNameProject(Long id, String newName) {

        Project project = projectRepository.findById(id).get();

        if (project != null){

            project.setName(newName);

            projectRepository.save(project);

            return true;
        }

        return false;
    }

    @Override
    public Boolean changeTypeProject(Long id, int newType) {

        Project project = projectRepository.findById(id).get();

        if (project != null){

            project.setType(newType);

            projectRepository.save(project);

            return true;
        }

        return false;
    }

    @Override
    public Boolean changeDescriptionProject(Long id, String description) {
        Project project = projectRepository.findById(id).get();

        if (project != null){

            project.setDescription(description);

            projectRepository.save(project);

            return true;
        }

        return false;
    }

    @Override
    public List<Project> getAllProjects() {

        List<Project> projects = new ArrayList<>();

        for (Project p: projectRepository.findAll()) {
            projects.add(p);
        }

        return projects;
    }

    @Override
    public Project findProjectByID(Long id) {

        Project project = projectRepository.findById(id).get();

        if (project != null){
            return project;
        }

        return null;
    }

    @Override
    public Project findProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public List<Project> findProjectsByType(int type) {
        List<Project> projects = new ArrayList<>();

        for (Project p: projectRepository.findByType(type)) {
            projects.add(p);
        }

        return projects;
    }

    @Override
    public void addGroupInProject(Long groupId, Long projectId) {

        Project project = projectRepository.findById(projectId).get();

        Group group = groupRepository.findById(groupId).get();

        List<Group> groups = project.getGroups();

        if(groups == null){

            groups = new ArrayList<>();

            groups.add(group);

            project.setGroups(groups);
        }else {

            groups.add(group);

            project.setGroups(groups);

        }

    }

    @Override
    public void removeGroupFromProject(Long groupId, Long projectId) {

        Project project = projectRepository.findById(projectId).get();

        Group group = groupRepository.findById(groupId).get();

        List<Group> groupList = project.getGroups();

        for (int i = 0; i<groupList.size(); i++) {

            if (groupList.get(i).getId() == group.getId())
                groupList.remove(i);

        }

    }

    @Override
    public Boolean createNewCategory(String categoryName, Long projectId) {
        Project project = projectRepository.findById(projectId).get();

        Category category = categoryRepository.findByName(categoryName);

        if (category != null){

            category.setName(categoryName);
            category.setProject(project);

            categoryRepository.save(category);

            return true;
        }

        return false;
    }

    @Override
    public Boolean removeCategoryById(Long id) {

        Category category = categoryRepository.findById(id).get();

        if (category != null){

            categoryRepository.delete(category);

            return true;
        }

        return false;
    }
}
