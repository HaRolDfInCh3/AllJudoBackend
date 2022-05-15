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

import com.test.microservices.dto.NationalityDto;
import com.test.microservices.mappers.NationalityDtoToNationality;
import com.test.microservices.pojos.Nationality;
import com.test.microservices.repositories.NationalityRepository;

@RestController
public class NationalityController {
	NationalityDtoToNationality mapper;
	NationalityRepository objetRepo;
	public NationalityController(NationalityRepository repo,NationalityDtoToNationality m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getNationalityByIdMongo/{id}")
public ResponseEntity<NationalityDto> getNationality( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Nationality ab=objetRepo.findByIdMongo( id);
		NationalityDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<NationalityDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NationalityDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getNationalityById/{id}")
public ResponseEntity<NationalityDto> getNationality( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Nationality ab=objetRepo.findById( id);
		NationalityDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<NationalityDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NationalityDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllNationalitys")
public ResponseEntity<List<NationalityDto>> getNationality( ) {
	List<Nationality> lab=objetRepo.findAll();
	List<NationalityDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<NationalityDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addNationality")
public ResponseEntity<NationalityDto> addNationality(@RequestBody NationalityDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Nationality ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<NationalityDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<NationalityDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateNationality/{id}")
public ResponseEntity<NationalityDto> updateNationality(@PathVariable int id,@RequestBody NationalityDto dto) {
	if(objetRepo.existsById(id)) {
		Nationality ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<NationalityDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NationalityDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteNationality/{id}")
public ResponseEntity<NationalityDto> deleteNationality(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Nationality ab=objetRepo.deleteById(id);
		NationalityDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<NationalityDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NationalityDto>(HttpStatus.NOT_FOUND);
}



}
