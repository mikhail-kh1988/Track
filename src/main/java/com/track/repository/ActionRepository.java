package com.track.repository;

import java.util.List;
import com.track.entity.issue.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends CrudRepository<Action, Long> {

}
