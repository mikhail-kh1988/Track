package com.track.repository;

import com.track.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByName(String name);
    List<Project> findByType(int type);
}
