package com.track.repository;

import com.track.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String string);
    List<Category> findByProjectId(Long projectId);

}
