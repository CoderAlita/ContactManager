package com.example.contactManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contactManager.entity.ContactEntity;
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{

}
