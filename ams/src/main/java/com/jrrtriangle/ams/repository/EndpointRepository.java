package com.jrrtriangle.ams.repository;

import com.jrrtriangle.ams.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint,Long> {

    Optional<Endpoint> findByEndpointname(String name);
}
