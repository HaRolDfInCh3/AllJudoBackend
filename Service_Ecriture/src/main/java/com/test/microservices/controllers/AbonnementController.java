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

import com.test.microservices.dto.AbonnementDto;
import com.test.microservices.mappers.AbonnementDtoToAbonnement;
import com.test.microservices.pojos.Abonnement;
import com.test.microservices.repositories.AbonnementRepository;

@RestController
public class AbonnementController {
	AbonnementDtoToAbonnement mapper;
	AbonnementRepository abonnementRepo;
	public AbonnementController(AbonnementRepository repo,AbonnementDtoToAbonnement m) {
		this.abonnementRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getAbonnementByIdMongo/{id}")
public ResponseEntity<AbonnementDto> getAbonnement( @PathVariable String id) {
	if(abonnementRepo.existsByIdMongo(id)) {
		Abonnement ab=abonnementRepo.findByIdMongo( id);
		AbonnementDto abdto=mapper.objectToDto(ab);
		return new ResponseEntity<AbonnementDto>(abdto,HttpStatus.OK);
	}
	return new ResponseEntity<AbonnementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAbonnementById/{id}")
public ResponseEntity<AbonnementDto> getAbonnement( @PathVariable int id) {
	if(abonnementRepo.existsById(id)) {
		Abonnement ab=abonnementRepo.findById( id);
		AbonnementDto abdto=mapper.objectToDto(ab);
		return new ResponseEntity<AbonnementDto>(abdto,HttpStatus.OK);
	}
	return new ResponseEntity<AbonnementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllAbonnements")
public ResponseEntity<List<AbonnementDto>> getAbonnement( ) {
	List<Abonnement> lab=abonnementRepo.findAll();
	List<AbonnementDto> labdto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<AbonnementDto>>(labdto,HttpStatus.OK);
}
@PostMapping("/addAbonnement")
public ResponseEntity<AbonnementDto> addAbonnement(@RequestBody AbonnementDto abdto) {
	if(!abonnementRepo.existsById(abdto.getId())) {
		Abonnement ab=mapper.dtoToObject(abdto);
		abonnementRepo.save(ab);
		return new ResponseEntity<AbonnementDto>(abdto,HttpStatus.CREATED);
	}
	return new ResponseEntity<AbonnementDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateAbonnement/{id}")
public ResponseEntity<AbonnementDto> updateAbonnement(@PathVariable int id,@RequestBody AbonnementDto abdto) {
	if(abonnementRepo.existsById(id)) {
		Abonnement ab=mapper.dtoToObject(abdto);
		abonnementRepo.save(ab);
		return new ResponseEntity<AbonnementDto>(abdto,HttpStatus.OK);
	}
	return new ResponseEntity<AbonnementDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteAbonnement/{id}")
public ResponseEntity<AbonnementDto> deleteAbonnement(@PathVariable int id) {
	if(abonnementRepo.existsById(id)) {
		Abonnement ab=abonnementRepo.deleteById(id);
		AbonnementDto abdto=mapper.objectToDto(ab);
		return new ResponseEntity<AbonnementDto>(abdto,HttpStatus.OK);
	}
	return new ResponseEntity<AbonnementDto>(HttpStatus.NOT_FOUND);
}


}

