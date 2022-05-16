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

import com.test.microservices.dto.AnnonceDto;
import com.test.microservices.mappers.AnnonceDtoToAnnonce;
import com.test.microservices.pojos.Annonce;
import com.test.microservices.repositories.AnnonceRepository;

@RestController
public class AnnonceController {
	AnnonceRepository annonceRepo;
	AnnonceDtoToAnnonce mapper;
	public AnnonceController(AnnonceRepository repo,AnnonceDtoToAnnonce m) {
		this.annonceRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getAnnonceByIdMongo/{id}")
public ResponseEntity<AnnonceDto> getAnnonce( @PathVariable String id) {
	if(annonceRepo.existsByIdMongo(id)) {
		Annonce ab=annonceRepo.findByIdMongo( id);
		AnnonceDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<AnnonceDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAnnonceById/{id}")
public ResponseEntity<AnnonceDto> getAnnonce( @PathVariable int id) {
	if(annonceRepo.existsById(id)) {
		Annonce ab=annonceRepo.findById( id);
		AnnonceDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<AnnonceDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllAnnonces")
public ResponseEntity<List<AnnonceDto>> getAnnonce( ) {
	List<Annonce> lab=annonceRepo.findAll();
	List<AnnonceDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<AnnonceDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addAnnonce")
public ResponseEntity<AnnonceDto> addAnnonce(@RequestBody AnnonceDto dto) {
	if(!annonceRepo.existsById(dto.getId())) {
		Annonce ab=mapper.dtoToObject(dto);
		annonceRepo.save(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<AnnonceDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateAnnonce/{id}")
public ResponseEntity<AnnonceDto> updateAnnonce(@PathVariable int id,@RequestBody AnnonceDto dto) {
	if(annonceRepo.existsById(id)) {
		Annonce ab=mapper.dtoToObject(dto);
		annonceRepo.save(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<AnnonceDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteAnnonce/{id}")
public ResponseEntity<AnnonceDto> deleteAnnonce(@PathVariable int id) {
	if(annonceRepo.existsById(id)) {
		Annonce ab=annonceRepo.deleteById(id);
		AnnonceDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<AnnonceDto>(HttpStatus.NOT_FOUND);
}

}
