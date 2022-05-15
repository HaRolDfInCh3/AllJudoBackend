package com.test.microservices.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservices.dto.UserDto;
import com.test.microservices.mappers.UserDtoToUser;
import com.test.microservices.pojos.User;
import com.test.microservices.repositories.UserRepository;

@RestController
public class UserController {
	UserRepository userRepo;
	UserDtoToUser mapper;
	public UserController(UserRepository repo,UserDtoToUser m) {
		this.userRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getUserByIdMongo/{id}")
public ResponseEntity<UserDto> getUser( @PathVariable String id) {
	if(userRepo.existsByIdMongo(id)) {
		User ab=userRepo.findByIdMongo( id);
		UserDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getUserById/{id}")
public ResponseEntity<UserDto> getUser( @PathVariable int id) {
	if(userRepo.existsById(id)) {
		User ab=userRepo.findById( id);
		UserDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllUsers")
public ResponseEntity<List<UserDto>> getUser( ) {
	List<User> lab=userRepo.findAll();
	List<UserDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<UserDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addUser")
public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
	if(!userRepo.existsById(dto.getId())) {
		User ab=mapper.dtoToObject(dto);
		userRepo.save(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<UserDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateUser/{id}")
public ResponseEntity<UserDto> updateUser(@PathVariable int id,@RequestBody UserDto dto) {
	if(userRepo.existsById(id)) {
		User ab=mapper.dtoToObject(dto);
		userRepo.save(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteUser/{id}")
public ResponseEntity<UserDto> deleteUser(@PathVariable int id) {
	if(userRepo.existsById(id)) {
		User ab=userRepo.deleteById(id);
		UserDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}


}

