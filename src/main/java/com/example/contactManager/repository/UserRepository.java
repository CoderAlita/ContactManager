package com.example.contactManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contactManager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
