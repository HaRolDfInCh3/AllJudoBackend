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

import com.test.microservices.dto.EvenementimportantsDto;
import com.test.microservices.mappers.EvenementimportantsDtoToEvenementimportants;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.pojos.Evenementimportant;
import com.test.microservices.repositories.EvenementRepository;
import com.test.microservices.repositories.EvenementimportantRepository;
@RestController
public class EvenementimportantsController {
	EvenementimportantsDtoToEvenementimportants mapper;
	EvenementimportantRepository objetRepo;
	EvenementRepository evRepo;
	public EvenementimportantsController(EvenementRepository ev,EvenementimportantRepository repo,EvenementimportantsDtoToEvenementimportants m) {
		this.objetRepo=repo;
		this.mapper=m;
		this.evRepo=ev;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getEvenementImportantByIdMongo/{id}")
public ResponseEntity<EvenementimportantsDto> getEvenementimportants( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Evenementimportant ab=objetRepo.findByIdMongo( id);
		EvenementimportantsDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementimportantsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantsDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEvenementImportantById/{id}")
public ResponseEntity<EvenementimportantsDto> getEvenementimportants( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenementimportant ab=objetRepo.findById( id);
		EvenementimportantsDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementimportantsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantsDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvenementImportants")
public ResponseEntity<List<EvenementimportantsDto>> getEvenementimportants( ) {
	List<Evenementimportant> lab=objetRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<EvenementimportantsDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementimportantsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllEvenementImportantsDesc")
public ResponseEntity<List<EvenementimportantsDto>> getEvenementimportantsDesc( ) {
	List<Evenementimportant> lab=objetRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<EvenementimportantsDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementimportantsDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addEvenementImportant")
public ResponseEntity<EvenementimportantsDto> addEvenementimportants(@RequestBody EvenementimportantsDto dto) {
	Page<Evenementimportant> c2 =objetRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		int evid=dto.getEvenement_id();
		Evenementimportant ab=mapper.dtoToObject(dto);
		if(evRepo.existsById(evid)) {
			Evenement eve=evRepo.findById(evid);
			ab.setEvenement2(eve);
		}
		ab.setId(max+1);
		objetRepo.save(ab);
		return new ResponseEntity<EvenementimportantsDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateEvenementImportant/{id}")
public ResponseEntity<EvenementimportantsDto> updateEvenementimportants(@PathVariable int id,@RequestBody EvenementimportantsDto dto) {
	Evenementimportant ancien=objetRepo.findById(id);	
		String idMongo=ancien.getIdMongo();
		Evenementimportant ab=mapper.dtoToObject(dto);
		int evid=dto.getEvenement_id();
		if(evRepo.existsById(evid)) {
			Evenement eve=evRepo.findById(evid);
			ab.setEvenement2(eve);
		}
		ab.setIdMongo(idMongo);
		objetRepo.deleteById(idMongo);
		objetRepo.save(ab);
		return new ResponseEntity<EvenementimportantsDto>(dto,HttpStatus.OK);
}
@DeleteMapping("/deleteEvenementImportant/{id}")
public ResponseEntity<EvenementimportantsDto> deleteEvenementimportants(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenementimportant ab=objetRepo.deleteById(id);
		EvenementimportantsDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementimportantsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantsDto>(HttpStatus.NOT_FOUND);
}
}
