package com.example.springlearnpr1.repositories;

import com.example.springlearnpr1.models.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Authority, Long> {
    Authority findByAuthorityName(String authorityName);
}

