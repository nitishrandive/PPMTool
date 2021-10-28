package com.ndroid.ppmtool.repositories;

import com.ndroid.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsername(String userName);
    User getUserByUserId(Long id);

}
