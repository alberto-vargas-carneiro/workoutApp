package com.alberto.workoutapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alberto.workoutapp.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
