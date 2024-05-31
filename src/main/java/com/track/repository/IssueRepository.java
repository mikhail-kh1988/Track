package com.track.repository;

import com.track.entity.issue.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    List<Issue> findAllByStartDateBetween(LocalDateTime start, LocalDateTime end);
    List<Issue> findAllByEndDateBetween(LocalDateTime start, LocalDateTime end);
    List<Issue> findAllByCreateDateBetween(LocalDateTime start, LocalDateTime end);
    @Query(value = "select * from issues ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Issue> findByLastRecord();

    @Query(value =  "select * from issues i \n" +
                    "where closed = false \n" +
                    "and short_description like '%задача%';", nativeQuery = true)
    Optional<Issue> findByLikeShortDescription();

}
