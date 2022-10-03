package com.track.repository;

import com.track.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByLogin(String login);
    List<User> findUserByFullName(String name);

}
