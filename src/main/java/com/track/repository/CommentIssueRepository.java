package com.track.repository;

import com.track.entity.issue.IssueComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentIssueRepository extends CrudRepository<IssueComment, Long> {

}
