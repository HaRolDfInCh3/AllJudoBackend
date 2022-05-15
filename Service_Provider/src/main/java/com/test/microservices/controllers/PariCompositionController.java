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

import com.test.microservices.dto.Pari_compositionDto;
import com.test.microservices.mappers.Pari_compositionDtoToPari_composition;
import com.test.microservices.pojos.Pari_composition;
import com.test.microservices.repositories.Pari_compositionRepository;

@RestController
public class PariCompositionController {
	Pari_compositionRepository resultatRepo;
	Pari_compositionDtoToPari_composition mapper;
	public PariCompositionController(Pari_compositionRepository repo,Pari_compositionDtoToPari_composition m) {
		this.resultatRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getPari_compositionByIdMongo/{id}")
public ResponseEntity<Pari_compositionDto> getPari_composition( @PathVariable String id) {
	if(resultatRepo.existsByIdMongo(id)) {
		Pari_composition ab=resultatRepo.findByIdMongo( id);
		Pari_compositionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPari_compositionById/{id}")
public ResponseEntity<Pari_compositionDto> getPari_composition( @PathVariable int id) {
	if(resultatRepo.existsById(id)) {
		Pari_composition ab=resultatRepo.findById( id);
		Pari_compositionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllPari_compositions")
public ResponseEntity<List<Pari_compositionDto>> getPari_composition( ) {
	List<Pari_composition> lab=resultatRepo.findAll();
	List<Pari_compositionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Pari_compositionDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addPari_composition")
public ResponseEntity<Pari_compositionDto> addPari_composition(@RequestBody Pari_compositionDto dto) {
	if(!resultatRepo.existsById(dto.getId())) {
		Pari_composition ab=mapper.dtoToObject(dto);
		resultatRepo.save(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updatePari_composition/{id}")
public ResponseEntity<Pari_compositionDto> updatePari_composition(@PathVariable int id,@RequestBody Pari_compositionDto dto) {
	if(resultatRepo.existsById(id)) {
		Pari_composition ab=mapper.dtoToObject(dto);
		resultatRepo.save(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deletePari_composition/{id}")
public ResponseEntity<Pari_compositionDto> deletePari_composition(@PathVariable int id) {
	if(resultatRepo.existsById(id)) {
		Pari_composition ab=resultatRepo.deleteById(id);
		Pari_compositionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}

}
