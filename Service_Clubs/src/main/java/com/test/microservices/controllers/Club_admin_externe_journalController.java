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

import com.test.microservices.dto.Club_admin_externe_journalDto;
import com.test.microservices.mappers.Club_admin_externe_journalDtoToClub_admin_externe_journal;
import com.test.microservices.pojos.Club_admin_externe_journal;
import com.test.microservices.repositories.Club_admin_externe_journalRepository;
@RestController
public class Club_admin_externe_journalController {
	Club_admin_externe_journalDtoToClub_admin_externe_journal mapper;
	Club_admin_externe_journalRepository club_admin_externe_journalRepo;
	public Club_admin_externe_journalController(Club_admin_externe_journalRepository repo,Club_admin_externe_journalDtoToClub_admin_externe_journal m) {
		this.club_admin_externe_journalRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getClub_admin_externe_journalByIdMongo/{id}")
public ResponseEntity<Club_admin_externe_journalDto> getClub_admin_externe_journal( @PathVariable String id) {
	if(club_admin_externe_journalRepo.existsByIdMongo(id)) {
		Club_admin_externe_journal ab=club_admin_externe_journalRepo.findByIdMongo( id);
		Club_admin_externe_journalDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getClub_admin_externe_journalById/{id}")
public ResponseEntity<Club_admin_externe_journalDto> getClub_admin_externe_journal( @PathVariable int id) {
	if(club_admin_externe_journalRepo.existsById(id)) {
		Club_admin_externe_journal ab=club_admin_externe_journalRepo.findById( id);
		Club_admin_externe_journalDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllClub_admin_externe_journals")
public ResponseEntity<List<Club_admin_externe_journalDto>> getClub_admin_externe_journal( ) {
	List<Club_admin_externe_journal> lab=club_admin_externe_journalRepo.findAll();
	List<Club_admin_externe_journalDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Club_admin_externe_journalDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addClub_admin_externe_journal")
public ResponseEntity<Club_admin_externe_journalDto> addClub_admin_externe_journal(@RequestBody Club_admin_externe_journalDto dto) {
	if(!club_admin_externe_journalRepo.existsById(dto.getId())) {
		Club_admin_externe_journal ab=mapper.dtoToObject(dto);
		club_admin_externe_journalRepo.save(ab);
		return new ResponseEntity<Club_admin_externe_journalDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Club_admin_externe_journalDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateClub_admin_externe_journal/{id}")
public ResponseEntity<Club_admin_externe_journalDto> updateClub_admin_externe_journal(@PathVariable int id,@RequestBody Club_admin_externe_journalDto dto) {
	if(club_admin_externe_journalRepo.existsById(id)) {
		Club_admin_externe_journal ab=mapper.dtoToObject(dto);
		club_admin_externe_journalRepo.save(ab);
		return new ResponseEntity<Club_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteClub_admin_externe_journal/{id}")
public ResponseEntity<Club_admin_externe_journalDto> deleteClub_admin_externe_journal(@PathVariable int id) {
	if(club_admin_externe_journalRepo.existsById(id)) {
		Club_admin_externe_journal ab=club_admin_externe_journalRepo.deleteById(id);
		Club_admin_externe_journalDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}



}
