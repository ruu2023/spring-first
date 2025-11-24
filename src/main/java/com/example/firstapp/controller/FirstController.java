package com.example.firstapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstapp.entity.User;
import com.example.firstapp.repository.UserRepository;

@RestController
public class FirstController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/hello")
	public List<User> get() {
		System.out.println("データ一覧:");
		
		List<User> userList = userRepository.findAll();
		userList.forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Name: " + u.getName());
        });
        
		String name = userList.get(0).getName();
		
		return userList;
	}

}
