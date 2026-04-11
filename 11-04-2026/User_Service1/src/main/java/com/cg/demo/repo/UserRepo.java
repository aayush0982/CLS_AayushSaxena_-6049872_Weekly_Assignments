package com.cg.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.model.UserService;

public interface UserRepo extends JpaRepository<UserService, Integer>{

}
