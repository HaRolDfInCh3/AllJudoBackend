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

import com.test.microservices.dto.PartenaireDto;
import com.test.microservices.mappers.PartenaireDtoToPartenaire;
import com.test.microservices.pojos.Partenaire;
import com.test.microservices.repositories.PartenairesRepository;

@RestController
public class PartenairesController {
	PartenairesRepository partenaireRepo;
	PartenaireDtoToPartenaire mapper;
	public PartenairesController(PartenairesRepository repo,PartenaireDtoToPartenaire m) {
		this.partenaireRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getPartenaireByIdMongo/{id}")
public ResponseEntity<PartenaireDto> getPartenaire( @PathVariable String id) {
	if(partenaireRepo.existsByIdMongo(id)) {
		Partenaire ab=partenaireRepo.findByIdMongo( id);
		PartenaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<PartenaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PartenaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPartenaireById/{id}")
public ResponseEntity<PartenaireDto> getPartenaire( @PathVariable int id) {
	if(partenaireRepo.existsById(id)) {
		Partenaire ab=partenaireRepo.findById( id);
		PartenaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<PartenaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PartenaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllPartenaires")
public ResponseEntity<List<PartenaireDto>> getPartenaire( ) {
	List<Partenaire> lab=partenaireRepo.findAll();
	List<PartenaireDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<PartenaireDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addPartenaire")
public ResponseEntity<PartenaireDto> addPartenaire(@RequestBody PartenaireDto dto) {
	if(!partenaireRepo.existsById(dto.getId())) {
		Partenaire ab=mapper.dtoToObject(dto);
		partenaireRepo.save(ab);
		return new ResponseEntity<PartenaireDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<PartenaireDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updatePartenaire/{id}")
public ResponseEntity<PartenaireDto> updatePartenaire(@PathVariable int id,@RequestBody PartenaireDto dto) {
	if(partenaireRepo.existsById(id)) {
		Partenaire ab=mapper.dtoToObject(dto);
		partenaireRepo.save(ab);
		return new ResponseEntity<PartenaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PartenaireDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deletePartenaire/{id}")
public ResponseEntity<PartenaireDto> deletePartenaire(@PathVariable int id) {
	if(partenaireRepo.existsById(id)) {
		Partenaire ab=partenaireRepo.deleteById(id);
		PartenaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<PartenaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<PartenaireDto>(HttpStatus.NOT_FOUND);
}




}
