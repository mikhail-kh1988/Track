package com.track.repository;

import com.track.entity.pages.PageChildPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageChildPageRepository extends CrudRepository<PageChildPage, Long> {
}
