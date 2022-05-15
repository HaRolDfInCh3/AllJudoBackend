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

import com.test.microservices.dto.Bannieres_par_tailleDto;
import com.test.microservices.mappers.Bannieres_par_tailleDtoToBannieres_par_taille;
import com.test.microservices.pojos.Bannieres_par_taille;
import com.test.microservices.repositories.Bannieres_par_tailleRepository;

@RestController
public class Bannieres_par_tailleController {
	Bannieres_par_tailleRepository bannieres_par_tailleRepo;
	Bannieres_par_tailleDtoToBannieres_par_taille mapper;
	public Bannieres_par_tailleController(Bannieres_par_tailleRepository repo,Bannieres_par_tailleDtoToBannieres_par_taille m) {
		this.bannieres_par_tailleRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getBannieres_par_tailleByIdMongo/{id}")
public ResponseEntity<Bannieres_par_tailleDto> getBannieres_par_taille( @PathVariable String id) {
	if(bannieres_par_tailleRepo.existsByIdMongo(id)) {
		Bannieres_par_taille ab=bannieres_par_tailleRepo.findByIdMongo( id);
		Bannieres_par_tailleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Bannieres_par_tailleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Bannieres_par_tailleDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getBannieres_par_tailleById/{id}")
public ResponseEntity<Bannieres_par_tailleDto> getBannieres_par_taille( @PathVariable int id) {
	if(bannieres_par_tailleRepo.existsById(id)) {
		Bannieres_par_taille ab=bannieres_par_tailleRepo.findById( id);
		Bannieres_par_tailleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Bannieres_par_tailleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Bannieres_par_tailleDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getBannieres_par_taille/{taille}")
public ResponseEntity<List<Bannieres_par_tailleDto>> rechercheBannieres_par_taille( @PathVariable String taille) {
	
		List<Bannieres_par_taille> lab=bannieres_par_tailleRepo.rechercheParTaille(taille);
		List<Bannieres_par_tailleDto> ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<Bannieres_par_tailleDto>>(ldto,HttpStatus.OK);
	
}
@GetMapping("/getAllBannieres_par_tailles")
public ResponseEntity<List<Bannieres_par_tailleDto>> getBannieres_par_taille( ) {
	List<Bannieres_par_taille> lab=bannieres_par_tailleRepo.findAll();
	List<Bannieres_par_tailleDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Bannieres_par_tailleDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addBannieres_par_taille")
public ResponseEntity<Bannieres_par_tailleDto> addBannieres_par_taille(@RequestBody Bannieres_par_tailleDto dto) {
	if(!bannieres_par_tailleRepo.existsById(dto.getId())) {
		Bannieres_par_taille ab=mapper.dtoToObject(dto);
		bannieres_par_tailleRepo.save(ab);
		return new ResponseEntity<Bannieres_par_tailleDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Bannieres_par_tailleDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateBannieres_par_taille/{id}")
public ResponseEntity<Bannieres_par_tailleDto> updateBannieres_par_taille(@PathVariable int id,@RequestBody Bannieres_par_tailleDto dto) {
	if(bannieres_par_tailleRepo.existsById(id)) {
		Bannieres_par_taille ab=mapper.dtoToObject(dto);
		bannieres_par_tailleRepo.save(ab);
		return new ResponseEntity<Bannieres_par_tailleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Bannieres_par_tailleDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteBannieres_par_taille/{id}")
public ResponseEntity<Bannieres_par_tailleDto> deleteBannieres_par_taille(@PathVariable int id) {
	if(bannieres_par_tailleRepo.existsById(id)) {
		Bannieres_par_taille ab=bannieres_par_tailleRepo.deleteById(id);
		Bannieres_par_tailleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Bannieres_par_tailleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Bannieres_par_tailleDto>(HttpStatus.NOT_FOUND);
}


}
