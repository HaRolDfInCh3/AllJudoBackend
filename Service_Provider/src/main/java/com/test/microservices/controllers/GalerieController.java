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

import com.test.microservices.dto.GalerieDto;
import com.test.microservices.mappers.GalerieDtoToGalerie;
import com.test.microservices.pojos.Galerie;
import com.test.microservices.repositories.GalerieRepository;
@RestController
public class GalerieController {
	GalerieDtoToGalerie mapper;
	GalerieRepository objetRepo;
	public GalerieController(GalerieRepository repo,GalerieDtoToGalerie m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getGalerieByIdMongo/{id}")
public ResponseEntity<GalerieDto> getGalerie( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Galerie ab=objetRepo.findByIdMongo( id);
		GalerieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<GalerieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getGalerieById/{id}")
public ResponseEntity<GalerieDto> getGalerie( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Galerie ab=objetRepo.findById( id);
		GalerieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<GalerieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllGaleries")
public ResponseEntity<List<GalerieDto>> getGalerie( ) {
	List<Galerie> lab=objetRepo.findAll();
	List<GalerieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<GalerieDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addGalerie")
public ResponseEntity<GalerieDto> addGalerie(@RequestBody GalerieDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Galerie ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<GalerieDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateGalerie/{id}")
public ResponseEntity<GalerieDto> updateGalerie(@PathVariable int id,@RequestBody GalerieDto dto) {
	if(objetRepo.existsById(id)) {
		Galerie ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<GalerieDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteGalerie/{id}")
public ResponseEntity<GalerieDto> deleteGalerie(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Galerie ab=objetRepo.deleteById(id);
		GalerieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<GalerieDto>(HttpStatus.NOT_FOUND);
}



}
