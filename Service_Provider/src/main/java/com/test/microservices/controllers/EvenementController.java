package com.test.microservices.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.microservices.dto.EvenementDto;
import com.test.microservices.mappers.EvenementDtoToEvenement;
import com.test.microservices.pojos.Annee;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.repositories.EvenementRepository;
@RestController
public class EvenementController {
	EvenementDtoToEvenement mapper;
	EvenementRepository objetRepo;
	public EvenementController(EvenementRepository repo,EvenementDtoToEvenement m) {
		this.objetRepo=repo;
		this.mapper=m;
	}
@GetMapping("/getEvenementByIdMongo/{id}")
public ResponseEntity<EvenementDto> getEvenement( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Evenement ab=objetRepo.findByIdMongo( id);
		EvenementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEvenementById/{id}")
public ResponseEntity<EvenementDto> getEvenement( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenement ab=objetRepo.findById( id);
		EvenementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvenements")
public ResponseEntity<List<EvenementDto>> getEvenement( ) {
	List<Evenement> lab=objetRepo.findAll();
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getLastEvenements")
public ResponseEntity<List<EvenementDto>> getLastEvenement( ) {
	Page<Evenement> c2 =objetRepo.findAll(PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "ID")));
	List<Evenement> lab=c2.getContent();
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getNextEvenements")
public ResponseEntity<List<EvenementDto>> getNextEvenement( ) {
	List<Evenement> lab =objetRepo.findNextEvents();
	
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllEventsYears")
public ResponseEntity<List<Annee>> getAllEventsYears( ) {
	List<Annee> lab =objetRepo.getAllYears();
	return new ResponseEntity<List<Annee>>(lab,HttpStatus.OK);
}
@GetMapping("/getNextEvenementsDesc")
public ResponseEntity<List<EvenementDto>> getNextEvenementDesc( ) {
	List<Evenement> lab =objetRepo.findNextEventDescs(PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "DateDebut")));
	
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllInvalidesEvenements")
public ResponseEntity<List<EvenementDto>> getInvalidesEvenement( ) {
	List<Evenement> lab=objetRepo.findAllNonValide();
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addEvenement")
public ResponseEntity<EvenementDto> addEvenement(@RequestBody EvenementDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Evenement ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateEvenement/{id}")
public ResponseEntity<EvenementDto> updateEvenement(@PathVariable int id,@RequestBody EvenementDto dto) {
	if(objetRepo.existsById(id)) {
		Evenement ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteEvenement/{id}")
public ResponseEntity<EvenementDto> deleteEvenement(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenement ab=objetRepo.deleteById(id);
		EvenementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}

}
