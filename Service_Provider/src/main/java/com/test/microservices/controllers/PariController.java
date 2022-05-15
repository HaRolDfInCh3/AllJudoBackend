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

import com.test.microservices.dto.PariDto;
import com.test.microservices.mappers.PariDtoToPari;
import com.test.microservices.pojos.Pari;
import com.test.microservices.repositories.PariRepository;

@RestController
public class PariController {
	PariRepository pariRepo;
	PariDtoToPari mapper;
	public PariController(PariRepository repo,PariDtoToPari m) {
		this.pariRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getPariByIdMongo/{id}")
public ResponseEntity<PariDto> getPari( @PathVariable String id) {
	if(pariRepo.existsByIdMongo(id)) {
		Pari ab=pariRepo.findByIdMongo( id);
		PariDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<PariDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PariDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPariById/{id}")
public ResponseEntity<PariDto> getPari( @PathVariable int id) {
	if(pariRepo.existsById(id)) {
		Pari ab=pariRepo.findById( id);
		PariDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<PariDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PariDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllParis")
public ResponseEntity<List<PariDto>> getPari( ) {
	List<Pari> lab=pariRepo.findAll();
	List<PariDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<PariDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addPari")
public ResponseEntity<PariDto> addPari(@RequestBody PariDto dto) {
	if(!pariRepo.existsById(dto.getId())) {
		Pari ab=mapper.dtoToObject(dto);
		pariRepo.save(ab);
		return new ResponseEntity<PariDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<PariDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updatePari/{id}")
public ResponseEntity<PariDto> updatePari(@PathVariable int id,@RequestBody PariDto dto) {
	if(pariRepo.existsById(id)) {
		Pari ab=mapper.dtoToObject(dto);
		pariRepo.save(ab);
		return new ResponseEntity<PariDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PariDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deletePari/{id}")
public ResponseEntity<PariDto> deletePari(@PathVariable int id) {
	if(pariRepo.existsById(id)) {
		Pari ab=pariRepo.deleteById(id);
		PariDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<PariDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PariDto>(HttpStatus.NOT_FOUND);
}

}

