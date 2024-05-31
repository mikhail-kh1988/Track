package com.track.repository;

import com.track.entity.pages.ChildPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildPageRepository extends CrudRepository<ChildPage, Long> {

}
