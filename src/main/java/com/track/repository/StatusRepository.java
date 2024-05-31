package com.track.repository;

import com.track.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
    List<Status> findByProjectId(Long id);
    List<Status> findByTrackId(Long id);

}
