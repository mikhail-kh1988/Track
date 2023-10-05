package com.track.repository;

import com.track.entity.User;
import com.track.entity.issue.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    List<Attachment> findByName(String name);
    List<Attachment> findByUser(User user);

}
