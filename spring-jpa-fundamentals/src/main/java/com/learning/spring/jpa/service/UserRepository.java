package com.learning.spring.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.spring.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
