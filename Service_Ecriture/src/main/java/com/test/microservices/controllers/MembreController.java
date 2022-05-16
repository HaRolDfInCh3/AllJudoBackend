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

import com.test.microservices.dto.MembreDto;
import com.test.microservices.mappers.MembreDtoToMembre;
import com.test.microservices.pojos.Membre;
import com.test.microservices.repositories.MembreRepository;

@RestController
public class MembreController {
	MembreDtoToMembre mapper;
	MembreRepository objetRepo;
	public MembreController(MembreRepository repo,MembreDtoToMembre m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getMembreByIdMongo/{id}")
public ResponseEntity<MembreDto> getMembre( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Membre ab=objetRepo.findByIdMongo( id);
		MembreDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<MembreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<MembreDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getMembreById/{id}")
public ResponseEntity<MembreDto> getMembre( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Membre ab=objetRepo.findById( id);
		MembreDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<MembreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<MembreDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllMembres")
public ResponseEntity<List<MembreDto>> getMembre( ) {
	List<Membre> lab=objetRepo.findAll();
	List<MembreDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<MembreDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addMembre")
public ResponseEntity<MembreDto> addMembre(@RequestBody MembreDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Membre ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<MembreDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<MembreDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateMembre/{id}")
public ResponseEntity<MembreDto> updateMembre(@PathVariable int id,@RequestBody MembreDto dto) {
	if(objetRepo.existsById(id)) {
		Membre ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<MembreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<MembreDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteMembre/{id}")
public ResponseEntity<MembreDto> deleteMembre(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Membre ab=objetRepo.deleteById(id);
		MembreDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<MembreDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<MembreDto>(HttpStatus.NOT_FOUND);
}

}
