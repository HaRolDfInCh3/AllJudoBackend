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

import com.test.microservices.dto.EntrainementDto;
import com.test.microservices.mappers.EntrainementDtoToEntrainement;
import com.test.microservices.pojos.Entrainement;
import com.test.microservices.repositories.EntrainementRepository;
@RestController
public class EntrainementController {
	EntrainementDtoToEntrainement mapper;
	EntrainementRepository objetRepo;
	public EntrainementController(EntrainementRepository repo,EntrainementDtoToEntrainement m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getEntrainementByIdMongo/{id}")
public ResponseEntity<EntrainementDto> getEntrainement( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Entrainement ab=objetRepo.findByIdMongo( id);
		EntrainementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EntrainementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EntrainementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEntrainementById/{id}")
public ResponseEntity<EntrainementDto> getEntrainement( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Entrainement ab=objetRepo.findById( id);
		EntrainementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EntrainementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EntrainementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEntrainements")
public ResponseEntity<List<EntrainementDto>> getEntrainement( ) {
	List<Entrainement> lab=objetRepo.findAll();
	List<EntrainementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EntrainementDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addEntrainement")
public ResponseEntity<EntrainementDto> addEntrainement(@RequestBody EntrainementDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Entrainement ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EntrainementDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<EntrainementDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateEntrainement/{id}")
public ResponseEntity<EntrainementDto> updateEntrainement(@PathVariable int id,@RequestBody EntrainementDto dto) {
	if(objetRepo.existsById(id)) {
		Entrainement ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EntrainementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EntrainementDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteEntrainement/{id}")
public ResponseEntity<EntrainementDto> deleteEntrainement(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Entrainement ab=objetRepo.deleteById(id);
		EntrainementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EntrainementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EntrainementDto>(HttpStatus.NOT_FOUND);
}

}
