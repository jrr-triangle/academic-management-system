package com.jrrtriangle.ams.repository;

import com.jrrtriangle.ams.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);
}
