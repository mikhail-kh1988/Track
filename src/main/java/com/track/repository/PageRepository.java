package com.track.repository;

import com.track.entity.pages.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends CrudRepository<Page, Long> {
    List<Page> findByProjectId(long projectId);
    List<Page> findByCreateById(long userId);
    List<Page> findByAdminById(long userId);
    List<Page> findByStared(boolean isStar);
}
