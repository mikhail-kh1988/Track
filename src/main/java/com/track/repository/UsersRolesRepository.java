package com.track.repository;


import com.track.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends CrudRepository<UserRole, Long> {
}
