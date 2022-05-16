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

import com.test.microservices.dto.FormulaireDto;
import com.test.microservices.mappers.FormulaireDtoToFormulaire;
import com.test.microservices.pojos.Formulaire;
import com.test.microservices.repositories.FormulaireRepository;
@RestController
public class FormulaireController {
	FormulaireDtoToFormulaire mapper;
	FormulaireRepository objetRepo;
	public FormulaireController(FormulaireRepository repo,FormulaireDtoToFormulaire m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getFormulaireByIdMongo/{id}")
public ResponseEntity<FormulaireDto> getFormulaire( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Formulaire ab=objetRepo.findByIdMongo( id);
		FormulaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<FormulaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<FormulaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getFormulaireById/{id}")
public ResponseEntity<FormulaireDto> getFormulaire( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Formulaire ab=objetRepo.findById( id);
		FormulaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<FormulaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<FormulaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllFormulaires")
public ResponseEntity<List<FormulaireDto>> getFormulaire( ) {
	List<Formulaire> lab=objetRepo.findAll();
	List<FormulaireDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<FormulaireDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addFormulaire")
public ResponseEntity<FormulaireDto> addFormulaire(@RequestBody FormulaireDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Formulaire ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<FormulaireDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<FormulaireDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateFormulaire/{id}")
public ResponseEntity<FormulaireDto> updateFormulaire(@PathVariable int id,@RequestBody FormulaireDto dto) {
	if(objetRepo.existsById(id)) {
		Formulaire ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<FormulaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<FormulaireDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteFormulaire/{id}")
public ResponseEntity<FormulaireDto> deleteFormulaire(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Formulaire ab=objetRepo.deleteById(id);
		FormulaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<FormulaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<FormulaireDto>(HttpStatus.NOT_FOUND);
}



}
