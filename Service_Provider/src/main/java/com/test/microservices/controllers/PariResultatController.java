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

import com.test.microservices.dto.Pari_resultatDto;
import com.test.microservices.mappers.Pari_resultatDtoToPari_resultat;
import com.test.microservices.pojos.Pari_resultat;
import com.test.microservices.repositories.Pari_resultatRepository;

@RestController
public class PariResultatController {
	Pari_resultatRepository resultatRepo;
	Pari_resultatDtoToPari_resultat mapper;
	public PariResultatController(Pari_resultatRepository repo,Pari_resultatDtoToPari_resultat m) {
		this.resultatRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getPari_resultatByIdMongo/{id}")
public ResponseEntity<Pari_resultatDto> getPari_resultat( @PathVariable String id) {
	if(resultatRepo.existsByIdMongo(id)) {
		Pari_resultat ab=resultatRepo.findByIdMongo( id);
		Pari_resultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_resultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_resultatDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPari_resultatById/{id}")
public ResponseEntity<Pari_resultatDto> getPari_resultat( @PathVariable int id) {
	if(resultatRepo.existsById(id)) {
		Pari_resultat ab=resultatRepo.findById( id);
		Pari_resultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_resultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_resultatDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllPari_resultats")
public ResponseEntity<List<Pari_resultatDto>> getPari_resultat( ) {
	List<Pari_resultat> lab=resultatRepo.findAll();
	List<Pari_resultatDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Pari_resultatDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addPari_resultat")
public ResponseEntity<Pari_resultatDto> addPari_resultat(@RequestBody Pari_resultatDto dto) {
	if(!resultatRepo.existsById(dto.getId())) {
		Pari_resultat ab=mapper.dtoToObject(dto);
		resultatRepo.save(ab);
		return new ResponseEntity<Pari_resultatDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Pari_resultatDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updatePari_resultat/{id}")
public ResponseEntity<Pari_resultatDto> updatePari_resultat(@PathVariable int id,@RequestBody Pari_resultatDto dto) {
	if(resultatRepo.existsById(id)) {
		Pari_resultat ab=mapper.dtoToObject(dto);
		resultatRepo.save(ab);
		return new ResponseEntity<Pari_resultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_resultatDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deletePari_resultat/{id}")
public ResponseEntity<Pari_resultatDto> deletePari_resultat(@PathVariable int id) {
	if(resultatRepo.existsById(id)) {
		Pari_resultat ab=resultatRepo.deleteById(id);
		Pari_resultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_resultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_resultatDto>(HttpStatus.NOT_FOUND);
}


}
