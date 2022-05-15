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

import com.test.microservices.dto.Champion_populariteDto;
import com.test.microservices.mappers.Champion_populariteDtoToChampion_popularite;
import com.test.microservices.pojos.Champion_popularite;
import com.test.microservices.repositories.Champion_populariteRepository;

@RestController
public class Champion_populariteController {
	Champion_populariteRepository champion_populariteRepo;
	Champion_populariteDtoToChampion_popularite mapper;
	public Champion_populariteController(Champion_populariteRepository repo,Champion_populariteDtoToChampion_popularite m) {
		this.champion_populariteRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampion_populariteByIdMongo/{id}")
public ResponseEntity<Champion_populariteDto> getChampion_popularite( @PathVariable String id) {
	if(champion_populariteRepo.existsByIdMongo(id)) {
		Champion_popularite ab=champion_populariteRepo.findByIdMongo( id);
		Champion_populariteDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_populariteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_populariteDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampion_populariteById/{id}")
public ResponseEntity<Champion_populariteDto> getChampion_popularite( @PathVariable int id) {
	if(champion_populariteRepo.existsById(id)) {
		Champion_popularite ab=champion_populariteRepo.findById( id);
		Champion_populariteDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_populariteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_populariteDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampion_popularites")
public ResponseEntity<List<Champion_populariteDto>> getChampion_popularite( ) {
	List<Champion_popularite>lab=champion_populariteRepo.findAll();
	List<Champion_populariteDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Champion_populariteDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion_popularite")
public ResponseEntity<Champion_populariteDto> addChampion_popularite(@RequestBody Champion_populariteDto dto) {
	if(!champion_populariteRepo.existsById(dto.getId())) {
		Champion_popularite ab=mapper.dtoToObject(dto);
		champion_populariteRepo.save(ab);
		return new ResponseEntity<Champion_populariteDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Champion_populariteDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateChampion_popularite/{id}")
public ResponseEntity<Champion_populariteDto> updateChampion_popularite(@PathVariable int id,@RequestBody Champion_populariteDto dto) {
	if(champion_populariteRepo.existsById(id)) {
		Champion_popularite ab=mapper.dtoToObject(dto);
		champion_populariteRepo.save(ab);
		return new ResponseEntity<Champion_populariteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_populariteDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteChampion_popularite/{id}")
public ResponseEntity<Champion_populariteDto> deleteChampion_popularite(@PathVariable int id) {
	if(champion_populariteRepo.existsById(id)) {
		Champion_popularite ab=champion_populariteRepo.deleteById(id);
		Champion_populariteDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_populariteDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_populariteDto>(HttpStatus.NOT_FOUND);
}


}
