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

import com.test.microservices.dto.Club_horaireDto;
import com.test.microservices.mappers.Club_horaireDtoToClub_horaire;
import com.test.microservices.pojos.Club_horaire;
import com.test.microservices.repositories.Club_horaireRepository;
@RestController
public class Club_horairesController {
	Club_horaireDtoToClub_horaire mapper;
	Club_horaireRepository club_horaireRepo;
	public Club_horairesController(Club_horaireRepository repo,Club_horaireDtoToClub_horaire m) {
		this.club_horaireRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getClub_horaireByIdMongo/{id}")
public ResponseEntity<Club_horaireDto> getClub_horaire( @PathVariable String id) {
	if(club_horaireRepo.existsByIdMongo(id)) {
		Club_horaire ab=club_horaireRepo.findByIdMongo( id);
		Club_horaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_horaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_horaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getClub_horaireById/{id}")
public ResponseEntity<Club_horaireDto> getClub_horaire( @PathVariable int id) {
	if(club_horaireRepo.existsById(id)) {
		Club_horaire ab=club_horaireRepo.findById( id);
		Club_horaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_horaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_horaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllClub_horaires")
public ResponseEntity<List<Club_horaireDto>> getClub_horaire( ) {
	List<Club_horaire> lab=club_horaireRepo.findAll();
	List<Club_horaireDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Club_horaireDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addClub_horaire")
public ResponseEntity<Club_horaireDto> addClub_horaire(@RequestBody Club_horaireDto dto) {
	if(!club_horaireRepo.existsById(dto.getId())) {
		Club_horaire ab=mapper.dtoToObject(dto);
		club_horaireRepo.save(ab);
		return new ResponseEntity<Club_horaireDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Club_horaireDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateClub_horaire/{id}")
public ResponseEntity<Club_horaireDto> updateClub_horaire(@PathVariable int id,@RequestBody Club_horaireDto dto) {
	if(club_horaireRepo.existsById(id)) {
		Club_horaire ab=mapper.dtoToObject(dto);
		club_horaireRepo.save(ab);
		return new ResponseEntity<Club_horaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_horaireDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteClub_horaire/{id}")
public ResponseEntity<Club_horaireDto> deleteClub_horaire(@PathVariable int id) {
	if(club_horaireRepo.existsById(id)) {
		Club_horaire ab=club_horaireRepo.deleteById(id);
		Club_horaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_horaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_horaireDto>(HttpStatus.NOT_FOUND);
}



}
