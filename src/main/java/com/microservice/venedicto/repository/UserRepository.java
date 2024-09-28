package com.microservice.venedicto.repository;

import com.microservice.venedicto.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
