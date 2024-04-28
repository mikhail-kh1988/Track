package com.track.repository;

import com.track.entity.issue.IssueTimeCost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueTimeCostRepository extends CrudRepository<IssueTimeCost, Long> {

}
