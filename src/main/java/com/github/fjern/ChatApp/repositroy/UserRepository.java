package com.github.fjern.ChatApp.repositroy;

import com.github.fjern.ChatApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
