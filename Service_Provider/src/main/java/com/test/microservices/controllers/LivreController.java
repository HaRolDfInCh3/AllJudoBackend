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

import com.test.microservices.dto.LivreDto;
import com.test.microservices.mappers.LivreDtoToLivre;
import com.test.microservices.pojos.Livre;
import com.test.microservices.repositories.LivresRepository;

@RestController
public class LivreController {
	LivreDtoToLivre mapper;
	LivresRepository objetRepo;
	public LivreController(LivresRepository repo,LivreDtoToLivre m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getLivreByIdMongo/{id}")
public ResponseEntity<LivreDto> getLivre( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Livre ab=objetRepo.findByIdMongo( id);
		LivreDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LivreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LivreDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getLivreById/{id}")
public ResponseEntity<LivreDto> getLivre( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Livre ab=objetRepo.findById( id);
		LivreDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LivreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LivreDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllLivres")
public ResponseEntity<List<LivreDto>> getLivre( ) {
	List<Livre> lab=objetRepo.findAll();
	List<LivreDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<LivreDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addLivre")
public ResponseEntity<LivreDto> addLivre(@RequestBody LivreDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Livre ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<LivreDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<LivreDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateLivre/{id}")
public ResponseEntity<LivreDto> updateLivre(@PathVariable int id,@RequestBody LivreDto dto) {
	if(objetRepo.existsById(id)) {
		Livre ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<LivreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LivreDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteLivre/{id}")
public ResponseEntity<LivreDto> deleteLivre(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Livre ab=objetRepo.deleteById(id);
		LivreDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LivreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LivreDto>(HttpStatus.NOT_FOUND);
}


}
