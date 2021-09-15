package com.jrrtriangle.ams.repository;

import com.jrrtriangle.ams.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(String role);
}
