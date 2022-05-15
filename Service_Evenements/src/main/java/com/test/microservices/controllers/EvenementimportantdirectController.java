package com.test.microservices.controllers;

import java.util.List;

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

import com.test.microservices.dto.EvenementimportantdirectDto;
import com.test.microservices.mappers.EvenementimportantdirectDtoToEvenementimportantdirect;
import com.test.microservices.pojos.Evenementimportant;
import com.test.microservices.pojos.Evenementimportantdirect;
import com.test.microservices.repositories.EvenementimportantRepository;
import com.test.microservices.repositories.EvenementimportantdirectRepository;
@RestController
public class EvenementimportantdirectController {
	EvenementimportantdirectDtoToEvenementimportantdirect mapper;
	EvenementimportantdirectRepository objetRepo;
	EvenementimportantRepository eiRepo;
	public EvenementimportantdirectController(EvenementimportantRepository eiR,EvenementimportantdirectRepository repo,EvenementimportantdirectDtoToEvenementimportantdirect m) {
		this.objetRepo=repo;
		this.mapper=m;
		this.eiRepo=eiR;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getEvenementImportantDirectByIdMongo/{id}")
public ResponseEntity<EvenementimportantdirectDto> getEvenementimportantdirect( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Evenementimportantdirect ab=objetRepo.findByIdMongo( id);
		EvenementimportantdirectDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementimportantdirectDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantdirectDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEvenementImportantDirectById/{id}")
public ResponseEntity<EvenementimportantdirectDto> getEvenementimportantdirect( @PathVariable int id) {
	System.out.println("tr");
	if(objetRepo.existsById(id)) {
		
		Evenementimportantdirect ab=objetRepo.findById( id);
		EvenementimportantdirectDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementimportantdirectDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantdirectDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvenementImportantDirects")
public ResponseEntity<List<EvenementimportantdirectDto>> getEvenementimportantdirect( ) {
	List<Evenementimportantdirect> lab=objetRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<EvenementimportantdirectDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementimportantdirectDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllEvenementImportantDirectsDesc")
public ResponseEntity<List<EvenementimportantdirectDto>> getEvenementimportantdirectDesc( ) {
	List<Evenementimportantdirect> lab=objetRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<EvenementimportantdirectDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementimportantdirectDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addEvenementImportantDirect")
public ResponseEntity<EvenementimportantdirectDto> addEvenementimportantdirect(@RequestBody EvenementimportantdirectDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		int eid=dto.getEvenement_important_id();
		Evenementimportantdirect ab=mapper.dtoToObject(dto);
		if(eiRepo.existsById(eid)) {
			Evenementimportant ei=eiRepo.findById(eid);
			ab.setEvenementimportant2(ei);
		}
		
		objetRepo.save(ab);
		return new ResponseEntity<EvenementimportantdirectDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<EvenementimportantdirectDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateEvenementImportantDirect/{id}")
public ResponseEntity<EvenementimportantdirectDto> updateEvenementimportantdirect(@PathVariable int id,@RequestBody EvenementimportantdirectDto dto) {
	if(objetRepo.existsById(id)) {
		Evenementimportantdirect ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvenementimportantdirectDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantdirectDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteEvenementImportantDirect/{id}")
public ResponseEntity<EvenementimportantdirectDto> deleteEvenementimportantdirect(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenementimportantdirect ab=objetRepo.deleteById(id);
		EvenementimportantdirectDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementimportantdirectDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementimportantdirectDto>(HttpStatus.NOT_FOUND);
}
}
