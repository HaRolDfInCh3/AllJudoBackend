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
import com.test.microservices.mappers.LiensDtoToLiens;
import com.test.microservices.pojos.Liens;
import com.test.microservices.repositories.LiensRepository;

@RestController
public class LienController {
	LiensRepository liensRepo;
	LiensDtoToLiens mapper;
	public LienController(LiensRepository repo,LiensDtoToLiens m) {
		this.mapper=m;
		this.liensRepo=repo;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getLienByIdMongo/{id}")
public ResponseEntity<LiensDto> getLien( @PathVariable String id) {
	if(liensRepo.existsByIdMongo(id)) {
		Liens ab=liensRepo.findByIdMongo( id);
		LiensDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getLienById/{id}")
public ResponseEntity<LiensDto> getLien( @PathVariable int id) {
	if(liensRepo.existsById(id)) {
		Liens ab=liensRepo.findById( id);
		LiensDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllLiens")
public ResponseEntity<List<LiensDto>> getLiens( ) {
	List<Liens> lab=liensRepo.findAll();
	List<LiensDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<LiensDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addLien")
public ResponseEntity<LiensDto> addLien(@RequestBody LiensDto dto) {
	if(!liensRepo.existsById(dto.getId())) {
		Liens lien=mapper.dtoToObject(dto);
		liensRepo.save(lien);
		
		return new ResponseEntity<LiensDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateLien/{id}")
public ResponseEntity<LiensDto> updateLien(@PathVariable int id,@RequestBody LiensDto dto) {
	if(liensRepo.existsById(id)) {
		Liens ab=mapper.dtoToObject(dto);
		liensRepo.save(ab);
		
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteLien/{id}")
public ResponseEntity<LiensDto> deleteLien(@PathVariable int id) {
	if(liensRepo.existsById(id)) {
		Liens ab=liensRepo.deleteById(id);
		LiensDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LiensDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LiensDto>(HttpStatus.NOT_FOUND);
}


}
