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

import com.test.microservices.dto.Technique_specialisteDto;
import com.test.microservices.mappers.Technique_specialisteDtoToTechnique_specialiste;
import com.test.microservices.pojos.Technique_specialiste;
import com.test.microservices.repositories.Technique_specialisteRepository;

@RestController
public class TechniqueSpecialisteController {
	Technique_specialisteRepository tsRepo;
	Technique_specialisteDtoToTechnique_specialiste mapper;
	public TechniqueSpecialisteController(Technique_specialisteRepository repo,Technique_specialisteDtoToTechnique_specialiste m) {
		this.tsRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getTechnique_specialisteByIdMongo/{id}")
public ResponseEntity<Technique_specialisteDto> getTechnique_specialiste( @PathVariable String id) {
	if(tsRepo.existsByIdMongo(id)) {
		Technique_specialiste ab=tsRepo.findByIdMongo( id);
		Technique_specialisteDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Technique_specialisteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Technique_specialisteDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getTechnique_specialisteById/{id}")
public ResponseEntity<Technique_specialisteDto> getTechnique_specialiste( @PathVariable int id) {
	if(tsRepo.existsById(id)) {
		Technique_specialiste ab=tsRepo.findById( id);
		Technique_specialisteDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Technique_specialisteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Technique_specialisteDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllTechnique_specialistes")
public ResponseEntity<List<Technique_specialisteDto>> getTechnique_specialiste( ) {
	List<Technique_specialiste> lab=tsRepo.findAll();
	List<Technique_specialisteDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Technique_specialisteDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addTechnique_specialiste")
public ResponseEntity<Technique_specialisteDto> addTechnique_specialiste(@RequestBody Technique_specialisteDto dto) {
	if(!tsRepo.existsById(dto.getId())) {
		Technique_specialiste ab=mapper.dtoToObject(dto);
		tsRepo.save(ab);
		return new ResponseEntity<Technique_specialisteDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Technique_specialisteDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateTechnique_specialiste/{id}")
public ResponseEntity<Technique_specialisteDto> updateTechnique_specialiste(@PathVariable int id,@RequestBody Technique_specialisteDto dto) {
	if(tsRepo.existsById(id)) {
		Technique_specialiste ab=mapper.dtoToObject(dto);
		tsRepo.save(ab);
		return new ResponseEntity<Technique_specialisteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Technique_specialisteDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteTechnique_specialiste/{id}")
public ResponseEntity<Technique_specialisteDto> deleteTechnique_specialiste(@PathVariable int id) {
	if(tsRepo.existsById(id)) {
		Technique_specialiste ab=tsRepo.deleteById(id);
		Technique_specialisteDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Technique_specialisteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Technique_specialisteDto>(HttpStatus.NOT_FOUND);
}


}
