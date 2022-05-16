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

import com.test.microservices.dto.Champion_admin_externeDto;
import com.test.microservices.mappers.Champion_admin_externeDtoToChampion_admin_externe;
import com.test.microservices.pojos.Champion_admin_externe;
import com.test.microservices.repositories.ChampionAdminExterneRepository;
@RestController
public class Champion_admin_externeController {
	ChampionAdminExterneRepository cAERepo;
	Champion_admin_externeDtoToChampion_admin_externe mapper;
	public Champion_admin_externeController(ChampionAdminExterneRepository repo,Champion_admin_externeDtoToChampion_admin_externe m) {
		this.cAERepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampion_admin_externeByIdMongo/{id}")
public ResponseEntity<Champion_admin_externeDto> getChampion_admin_externe( @PathVariable String id) {
	if(cAERepo.existsByIdMongo(id)) {
		Champion_admin_externe ab=cAERepo.findByIdMongo( id);
		Champion_admin_externeDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externeDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampion_admin_externeById/{id}")
public ResponseEntity<Champion_admin_externeDto> getChampion_admin_externe( @PathVariable int id) {
	if(cAERepo.existsById(id)) {
		Champion_admin_externe ab=cAERepo.findById( id);
		Champion_admin_externeDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externeDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampion_admin_externes")
public ResponseEntity<List<Champion_admin_externeDto>> getChampion_admin_externe( ) {
	List<Champion_admin_externe> lab=cAERepo.findAll();
	List<Champion_admin_externeDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Champion_admin_externeDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion_admin_externe")
public ResponseEntity<Champion_admin_externeDto> addChampion_admin_externe(@RequestBody Champion_admin_externeDto dto) {
	if(!cAERepo.existsById(dto.getId())) {
		Champion_admin_externe ab=mapper.dtoToObject(dto);
		cAERepo.save(ab);
		
		return new ResponseEntity<Champion_admin_externeDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Champion_admin_externeDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateChampion_admin_externe/{id}")
public ResponseEntity<Champion_admin_externeDto> updateChampion_admin_externe(@PathVariable int id,@RequestBody Champion_admin_externeDto dto) {
	if(cAERepo.existsById(id)) {
		Champion_admin_externe ab=mapper.dtoToObject(dto);
		cAERepo.save(ab);
		return new ResponseEntity<Champion_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externeDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteChampion_admin_externe/{id}")
public ResponseEntity<Champion_admin_externeDto> deleteChampion_admin_externe(@PathVariable int id) {
	if(cAERepo.existsById(id)) {
		Champion_admin_externe ab=cAERepo.deleteById(id);
		Champion_admin_externeDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externeDto>(HttpStatus.NOT_FOUND);
}


}
