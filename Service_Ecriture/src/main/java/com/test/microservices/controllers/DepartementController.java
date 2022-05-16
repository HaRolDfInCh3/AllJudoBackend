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

import com.test.microservices.dto.DepartementDto;
import com.test.microservices.mappers.DepartementDtoToDepartement;
import com.test.microservices.pojos.Departement;
import com.test.microservices.repositories.DepartementRepository;
@RestController
public class DepartementController {
	DepartementDtoToDepartement mapper;
	DepartementRepository depRepo;
	public DepartementController(DepartementRepository repo,DepartementDtoToDepartement m) {
		this.depRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getDepartementByIdMongo/{id}")
public ResponseEntity<DepartementDto> getDepartement( @PathVariable String id) {
	if(depRepo.existsByIdMongo(id)) {
		Departement ab=depRepo.findByIdMongo( id);
		DepartementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<DepartementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<DepartementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getDepartementById/{id}")
public ResponseEntity<DepartementDto> getDepartement( @PathVariable int id) {
	if(depRepo.existsById(id)) {
		Departement ab=depRepo.findById( id);
		DepartementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<DepartementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<DepartementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllDepartements")
public ResponseEntity<List<DepartementDto>> getDepartement( ) {
	List<Departement> lab=depRepo.findAll();
	List<DepartementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<DepartementDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addDepartement")
public ResponseEntity<DepartementDto> addDepartement(@RequestBody DepartementDto dto) {
	if(!depRepo.existsById(dto.getId())) {
		Departement ab=mapper.dtoToObject(dto);
		depRepo.save(ab);
		return new ResponseEntity<DepartementDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<DepartementDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateDepartement/{id}")
public ResponseEntity<DepartementDto> updateDepartement(@PathVariable int id,@RequestBody DepartementDto dto) {
	if(depRepo.existsById(id)) {
		Departement ab=mapper.dtoToObject(dto);
		depRepo.save(ab);
		return new ResponseEntity<DepartementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<DepartementDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteDepartement/{id}")
public ResponseEntity<DepartementDto> deleteDepartement(@PathVariable int id) {
	if(depRepo.existsById(id)) {
		Departement ab=depRepo.deleteById(id);
		DepartementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<DepartementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<DepartementDto>(HttpStatus.NOT_FOUND);
}

	
}
