package com.example.springlearnpr1.repositories;

import com.example.springlearnpr1.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

}
