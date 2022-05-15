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

import com.test.microservices.dto.CategorieDto;
import com.test.microservices.mappers.CategorieDtoToCategorie;
import com.test.microservices.pojos.Categorie;
import com.test.microservices.repositories.CategorieRepository;
@RestController
public class CategorieController {
	CategorieRepository categorieRepo;
	CategorieDtoToCategorie mapper;
	public CategorieController(CategorieRepository repo,CategorieDtoToCategorie m) {
		this.categorieRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getCategorieByIdMongo/{id}")
public ResponseEntity<CategorieDto> getCategorie( @PathVariable String id) {
	if(categorieRepo.existsByIdMongo(id)) {
		Categorie ab=categorieRepo.findByIdMongo( id);
		CategorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CategorieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getCategorieById/{id}")
public ResponseEntity<CategorieDto> getCategorie( @PathVariable int id) {
	if(categorieRepo.existsById(id)) {
		Categorie ab=categorieRepo.findById( id);
		CategorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CategorieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllCategories")
public ResponseEntity<List<CategorieDto>> getCategorie( ) {
	List<Categorie> lab=categorieRepo.findAll();
	List<CategorieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<CategorieDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addCategorie")
public ResponseEntity<CategorieDto> addCategorie(@RequestBody CategorieDto dto) {
	if(!categorieRepo.existsById(dto.getId())) {
		Categorie ab=mapper.dtoToObject(dto);
		categorieRepo.save(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<CategorieDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateCategorie/{id}")
public ResponseEntity<CategorieDto> updateCategorie(@PathVariable int id,@RequestBody CategorieDto dto) {
	if(categorieRepo.existsById(id)) {
		Categorie ab=mapper.dtoToObject(dto);
		categorieRepo.save(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CategorieDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteCategorie/{id}")
public ResponseEntity<CategorieDto> deleteCategorie(@PathVariable int id) {
	if(categorieRepo.existsById(id)) {
		Categorie ab=categorieRepo.deleteById(id);
		CategorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CategorieDto>(HttpStatus.NOT_FOUND);
}


}
