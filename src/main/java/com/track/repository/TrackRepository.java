package com.track.repository;

import com.track.entity.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findByProjectId(Long projectId);

}
