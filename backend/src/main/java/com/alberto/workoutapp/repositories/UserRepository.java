package com.alberto.workoutapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.workoutapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByEmail(String email);
}
