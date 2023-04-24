package com.track.service.impl;

import com.track.entity.Project;
import com.track.repository.ProjectRepository;
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
}
