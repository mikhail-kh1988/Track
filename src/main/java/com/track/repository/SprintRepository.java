package com.track.repository;

import com.track.entity.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    Sprint findByName(String name);


}
