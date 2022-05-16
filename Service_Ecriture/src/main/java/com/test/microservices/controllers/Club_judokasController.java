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

import com.test.microservices.dto.Club_judokaDto;
import com.test.microservices.mappers.Club_judokaDtoToClub_judoka;
import com.test.microservices.pojos.Club_judoka;
import com.test.microservices.repositories.Club_judokaRepository;
@RestController
public class Club_judokasController {
	Club_judokaDtoToClub_judoka mapper;
	Club_judokaRepository club_judokaRepo;
	public Club_judokasController(Club_judokaRepository repo,Club_judokaDtoToClub_judoka m) {
		this.club_judokaRepo=repo;
		this.mapper=m;
	}
@GetMapping("/getClub_judokaByIdMongo/{id}")
public ResponseEntity<Club_judokaDto> getClub_judoka( @PathVariable String id) {
	if(club_judokaRepo.existsByIdMongo(id)) {
		Club_judoka ab=club_judokaRepo.findByIdMongo( id);
		Club_judokaDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_judokaDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_judokaDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getClub_judokaById/{id}")
public ResponseEntity<Club_judokaDto> getClub_judoka( @PathVariable int id) {
	if(club_judokaRepo.existsById(id)) {
		Club_judoka ab=club_judokaRepo.findById( id);
		Club_judokaDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_judokaDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_judokaDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllClub_judokas")
public ResponseEntity<List<Club_judokaDto>> getClub_judoka( ) {
	List<Club_judoka> lab=club_judokaRepo.findAll();
	List<Club_judokaDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Club_judokaDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addClub_judoka")
public ResponseEntity<Club_judokaDto> addClub_judoka(@RequestBody Club_judokaDto dto) {
	if(!club_judokaRepo.existsById(dto.getId())) {
		Club_judoka ab=mapper.dtoToObject(dto);
		club_judokaRepo.save(ab);
		return new ResponseEntity<Club_judokaDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Club_judokaDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateClub_judoka/{id}")
public ResponseEntity<Club_judokaDto> updateClub_judoka(@PathVariable int id,@RequestBody Club_judokaDto dto) {
	if(club_judokaRepo.existsById(id)) {
		Club_judoka ab=mapper.dtoToObject(dto);
		club_judokaRepo.save(ab);
		return new ResponseEntity<Club_judokaDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_judokaDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteClub_judoka/{id}")
public ResponseEntity<Club_judokaDto> deleteClub_judoka(@PathVariable int id) {
	if(club_judokaRepo.existsById(id)) {
		Club_judoka ab=club_judokaRepo.deleteById(id);
		Club_judokaDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_judokaDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_judokaDto>(HttpStatus.NOT_FOUND);
}




}
