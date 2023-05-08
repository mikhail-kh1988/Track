package com.track.repository;

import com.track.entity.SprintIssue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintIssueRepository extends CrudRepository<SprintIssue, Long> {
}
