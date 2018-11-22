package com.capgemini.data;

import com.capgemini.domain.Users;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    Optional<Users> findByName(String username);
}