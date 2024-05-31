package com.track.ui.service;


import com.track.entity.Project;
import com.track.entity.Status;
import com.track.entity.Track;
import com.track.exception.NotFoundStatusByTrack;
import com.track.repository.ProjectRepository;
import com.track.repository.StatusRepository;
import com.track.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UIProjectService {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StatusRepository statusRepository;

    public void createNewProject(Project project){
        project.setCreateDate(LocalDateTime.now());
        projectRepository.save(project);
    }

    public void updateProject(Project project){
        projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    public List<Status> getStatusesByProject(long id){
        return projectService.getStatusesByProject(id);
    }

    public List<Track> getTracksByProject(long id){
        return projectService.getTracksByProject(id);
    }

    public void createNewTrack(String track, long projectId){
        projectService.createNewTrack(track, projectId);
    }

    public List<Status> getStatusesByTrack(long idTrack){

        List<Status> statuses = new ArrayList<>();

        if (statusRepository.findByTrackId(idTrack).isEmpty())
            throw  new NotFoundStatusByTrack("Not found status by this track!");

        statuses = statusRepository.findByTrackId(idTrack);

        return statuses;
    }


}
