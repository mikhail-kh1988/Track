package com.track.repository;


import com.track.entity.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {
}
