package com.track.repository;

import com.track.entity.issue.IssueBind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BindIssueRepository extends CrudRepository<IssueBind, Long> {
}
