package com.track.repository;

import com.track.entity.issue.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<Attachment, Long> {


}
