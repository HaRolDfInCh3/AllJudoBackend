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

import com.test.microservices.dto.Pari_userDto;
import com.test.microservices.mappers.Pari_userDtoToPari_user;
import com.test.microservices.pojos.Pari_user;
import com.test.microservices.repositories.Pari_userRepository;

@RestController
public class PariUserController {
	Pari_userRepository puRepo;
	Pari_userDtoToPari_user mapper;
	public PariUserController(Pari_userRepository repo,Pari_userDtoToPari_user m) {
		this.puRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getPari_userByIdMongo/{id}")
public ResponseEntity<Pari_userDto> getPari_user( @PathVariable String id) {
	if(puRepo.existsByIdMongo(id)) {
		Pari_user ab=puRepo.findByIdMongo( id);
		Pari_userDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_userDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_userDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPari_userById/{id}")
public ResponseEntity<Pari_userDto> getPari_user( @PathVariable int id) {
	if(puRepo.existsById(id)) {
		Pari_user ab=puRepo.findById( id);
		Pari_userDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_userDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_userDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllPari_users")
public ResponseEntity<List<Pari_userDto>> getPari_user( ) {
	List<Pari_user> lab=puRepo.findAll();
	List<Pari_userDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Pari_userDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addPari_user")
public ResponseEntity<Pari_userDto> addPari_user(@RequestBody Pari_userDto dto) {
	if(!puRepo.existsById(dto.getId())) {
		Pari_user ab=mapper.dtoToObject(dto);
		puRepo.save(ab);
		return new ResponseEntity<Pari_userDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Pari_userDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updatePari_user/{id}")
public ResponseEntity<Pari_userDto> updatePari_user(@PathVariable int id,@RequestBody Pari_userDto dto) {
	if(puRepo.existsById(id)) {
		Pari_user ab=mapper.dtoToObject(dto);
		puRepo.save(ab);
		return new ResponseEntity<Pari_userDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_userDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deletePari_user/{id}")
public ResponseEntity<Pari_userDto> deletePari_user(@PathVariable int id) {
	if(puRepo.existsById(id)) {
		Pari_user ab=puRepo.deleteById(id);
		Pari_userDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_userDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_userDto>(HttpStatus.NOT_FOUND);
}



}
