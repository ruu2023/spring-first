package com.example.firstapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.firstapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByName(String name);
	
	List<User> findByIdIn(List<Long> ids);

}
