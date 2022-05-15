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

import com.test.microservices.dto.LiensDto;
import com.test.microservices.mappers.LiensDtoToLiens2;
import com.test.microservices.pojos.Liens2;
import com.test.microservices.repositories.Liens2Repository;

@RestController
public class Lien2Controller {
	Liens2Repository liens2Repo;
	LiensDtoToLiens2 mapper;
	public Lien2Controller(Liens2Repository repo,LiensDtoToLiens2 m) {
		this.mapper=m;
		this.liens2Repo=repo;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getLien2ByIdMongo/{id}")
public ResponseEntity<LiensDto> getLien2( @PathVariable String id) {
	if(liens2Repo.existsByIdMongo(id)) {
		Liens2 ab=liens2Repo.findByIdMongo( id);
		LiensDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getLien2ById/{id}")
public ResponseEntity<LiensDto> getLien2( @PathVariable int id) {
	if(liens2Repo.existsById(id)) {
		Liens2 ab=liens2Repo.findById( id);
		LiensDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllLien2s")
public ResponseEntity<List<LiensDto>> getLien2s( ) {
	List<Liens2> lab=liens2Repo.findAll();
	List<LiensDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<LiensDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addLien2")
public ResponseEntity<LiensDto> addLien2(@RequestBody LiensDto dto) {
	if(!liens2Repo.existsById(dto.getId())) {
		Liens2 ab=mapper.dtoToObject(dto);
		liens2Repo.save(ab);
		
		return new ResponseEntity<LiensDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateLien2/{id}")
public ResponseEntity<LiensDto> updateLien2(@PathVariable int id,@RequestBody LiensDto dto) {
	if(liens2Repo.existsById(id)) {
		Liens2 ab=mapper.dtoToObject(dto);
		liens2Repo.save(ab);
		
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteLien2/{id}")
public ResponseEntity<LiensDto> deleteLien2(@PathVariable int id) {
	if(liens2Repo.existsById(id)) {
		Liens2 ab=liens2Repo.deleteById(id);
		LiensDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}



}
