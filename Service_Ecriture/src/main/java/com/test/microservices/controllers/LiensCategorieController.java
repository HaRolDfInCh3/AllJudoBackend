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

import com.test.microservices.dto.LienscategorieDto;
import com.test.microservices.mappers.LienscategorieDtoToLienscategorie;
import com.test.microservices.pojos.Lienscategorie;
import com.test.microservices.repositories.LienscategorieRepository;

@RestController
public class LiensCategorieController {
	LienscategorieDtoToLienscategorie mapper;
	LienscategorieRepository objetRepo;
	public LiensCategorieController(LienscategorieRepository repo,LienscategorieDtoToLienscategorie m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getLienscategorieByIdMongo/{id}")
public ResponseEntity<LienscategorieDto> getLienscategorie( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Lienscategorie ab=objetRepo.findByIdMongo( id);
		LienscategorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LienscategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LienscategorieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getLienscategorieById/{id}")
public ResponseEntity<LienscategorieDto> getLienscategorie( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Lienscategorie ab=objetRepo.findById( id);
		LienscategorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LienscategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LienscategorieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllLienscategories")
public ResponseEntity<List<LienscategorieDto>> getLienscategorie( ) {
	List<Lienscategorie> lab=objetRepo.findAll();
	List<LienscategorieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<LienscategorieDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addLienscategorie")
public ResponseEntity<LienscategorieDto> addLienscategorie(@RequestBody LienscategorieDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Lienscategorie ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<LienscategorieDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<LienscategorieDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateLienscategorie/{id}")
public ResponseEntity<LienscategorieDto> updateLienscategorie(@PathVariable int id,@RequestBody LienscategorieDto dto) {
	if(objetRepo.existsById(id)) {
		Lienscategorie ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<LienscategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LienscategorieDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteLienscategorie/{id}")
public ResponseEntity<LienscategorieDto> deleteLienscategorie(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Lienscategorie ab=objetRepo.deleteById(id);
		LienscategorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<LienscategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<LienscategorieDto>(HttpStatus.NOT_FOUND);
}
	

}
