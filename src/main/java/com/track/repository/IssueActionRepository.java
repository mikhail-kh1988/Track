package com.track.repository;

import com.track.entity.issue.IssueAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueActionRepository extends CrudRepository<IssueAction, Long> {

}
