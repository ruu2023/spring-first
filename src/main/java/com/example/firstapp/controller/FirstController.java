package com.example.firstapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstapp.api.dto.UserResponseDto;
import com.example.firstapp.configs.ApiRoutes;
import com.example.firstapp.entity.User;
import com.example.firstapp.repository.UserRepository;

@RestController
public class FirstController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/hello")
	public List<UserResponseDto> get() {
		System.out.println("データ一覧:");
		
		List<User> users = userRepository.findAll();
		users.forEach(u -> {
            System.out.println("ID: " + u.getId() + ", Name: " + u.getName());
        });
        
		return users.stream()
		        .map(UserResponseDto::from) // メソッド参照でスッキリ記述
		        .collect(Collectors.toList()); // Java 8では .toList() ではなくこれが必要
	}
	
	// GET by name List
	@GetMapping(ApiRoutes.User.GETBYNAME)
	public List<UserResponseDto> getByUserName(@PathVariable String name) {
		List<User> users = userRepository.findByName(name);
		return users.stream()
				.map(UserResponseDto::from)
				.collect(Collectors.toList());
	}

	// GET by name List
	@GetMapping(ApiRoutes.User.GET_NAMES_BY_IDS)
	public List<User> getNamesByIds(@RequestParam("id") List<Long> ids) {
		return userRepository.findByIdIn(ids);
	}
	
	@PostMapping(ApiRoutes.User.CREATE)
	public String create(@RequestBody User body) {
		User user = new User();
		user.setName(body.getName());
		userRepository.save(user);
		return "created";
	}

}
