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

import com.test.microservices.dto.Champion_admin_externe_palmaresDto;
import com.test.microservices.mappers.Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares;
import com.test.microservices.pojos.Champion_admin_externe_palmares;
import com.test.microservices.repositories.ChampionAdminExternePalmaresRepository;
@RestController
public class Champion_admin_externe_palmaresController {
	ChampionAdminExternePalmaresRepository cAEPRepo;
	Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares mapper;
	public Champion_admin_externe_palmaresController(ChampionAdminExternePalmaresRepository repo,Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares m) {
		this.cAEPRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampion_admin_externe_palmaresByIdMongo/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> getChampion_admin_externe_palmares( @PathVariable String id) {
	if(cAEPRepo.existsByIdMongo(id)) {
		Champion_admin_externe_palmares ab=cAEPRepo.findByIdMongo( id);
		Champion_admin_externe_palmaresDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampion_admin_externe_palmaresById/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> getChampion_admin_externe_palmares( @PathVariable int id) {
	if(cAEPRepo.existsById(id)) {
		Champion_admin_externe_palmares ab=cAEPRepo.findById( id);
		Champion_admin_externe_palmaresDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampion_admin_externe_palmaress")
public ResponseEntity<List<Champion_admin_externe_palmaresDto>> getChampion_admin_externe_palmares( ) {
	List<Champion_admin_externe_palmares> lab=cAEPRepo.findAll();
	List<Champion_admin_externe_palmaresDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Champion_admin_externe_palmaresDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion_admin_externe_palmares")
public ResponseEntity<Champion_admin_externe_palmaresDto> addChampion_admin_externe_palmares(@RequestBody Champion_admin_externe_palmaresDto dto) {
	if(!cAEPRepo.existsById(dto.getId())) {
		Champion_admin_externe_palmares ab=mapper.dtoToObject(dto);
		cAEPRepo.save(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateChampion_admin_externe_palmares/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> updateChampion_admin_externe_palmares(@PathVariable int id,@RequestBody Champion_admin_externe_palmaresDto dto) {
	if(cAEPRepo.existsById(id)) {
		Champion_admin_externe_palmares ab=mapper.dtoToObject(dto);
		cAEPRepo.save(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteChampion_admin_externe_palmares/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> deleteChampion_admin_externe_palmares(@PathVariable int id) {
	if(cAEPRepo.existsById(id)) {
		Champion_admin_externe_palmares ab=cAEPRepo.deleteById(id);
		Champion_admin_externe_palmaresDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}


}
