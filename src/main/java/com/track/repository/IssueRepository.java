package com.track.repository;

import com.track.entity.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {

    Issue findByExternalId(String externalId);
    List<Issue> findByProjectId(Long projectId);
    List<Issue> findByStatusId(Long statusId);
    List<Issue> findByPriority(int priority);
    List<Issue> findByTrackName(String trackName);
    List<Issue> findByLose(Boolean isLose);
    List<Issue> findByAssignId(Long assignId);
    List<Issue> findByCreateById(Long createById);


}
