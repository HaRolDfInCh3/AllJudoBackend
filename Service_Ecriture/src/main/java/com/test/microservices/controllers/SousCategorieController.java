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

import com.test.microservices.dto.Sous_categorieDto;
import com.test.microservices.mappers.Sous_categorieDtoToSous_categorie;
import com.test.microservices.pojos.Categorie;
import com.test.microservices.pojos.Sous_categorie;
import com.test.microservices.repositories.CategorieRepository;
import com.test.microservices.repositories.Sous_categorieRepository;


@RestController
public class SousCategorieController {
	Sous_categorieRepository userRepo;
	CategorieRepository catRepo;
	Sous_categorieDtoToSous_categorie mapper;
	public SousCategorieController(CategorieRepository c,Sous_categorieRepository repo,Sous_categorieDtoToSous_categorie m) {
		this.userRepo=repo;
		this.mapper=m;
		this.catRepo=c;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getSous_categorieByIdMongo/{id}")
public ResponseEntity<Sous_categorieDto> getSous_categorie( @PathVariable String id) {
	if(userRepo.existsByIdMongo(id)) {
		Sous_categorie ab=userRepo.findByIdMongo( id);
		Sous_categorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Sous_categorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Sous_categorieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getSous_categorieById/{id}")
public ResponseEntity<Sous_categorieDto> getSous_categorie( @PathVariable int id) {
	if(userRepo.existsById(id)) {
		Sous_categorie ab=userRepo.findById( id);
		Sous_categorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Sous_categorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Sous_categorieDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllSous_categories")
public ResponseEntity<List<Sous_categorieDto>> getSous_categorie( ) {
	List<Sous_categorie> lab=userRepo.findAll();
	List<Sous_categorieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Sous_categorieDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addSous_categorie")
public ResponseEntity<Sous_categorieDto> addSous_categorie(@RequestBody Sous_categorieDto dto) {
	Page<Sous_categorie> c2 =userRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
	int catId=dto.getCategorie_ID();
	Sous_categorie ab=mapper.dtoToObject(dto);
	if(this.catRepo.existsById(catId)) {
		Categorie cat=catRepo.findById(catId);
		ab.setCategorie2(cat);
		
	}
		
		ab.setId(max+1);
		dto.setId(max+1);
		userRepo.save(ab);
		return new ResponseEntity<Sous_categorieDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateSous_categorie/{id}")
public ResponseEntity<Sous_categorieDto> updateSous_categorie(@PathVariable int id,@RequestBody Sous_categorieDto dto) {
	if(userRepo.existsById(id)) {
		Sous_categorie ab=mapper.dtoToObject(dto);
		userRepo.save(ab);
		return new ResponseEntity<Sous_categorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Sous_categorieDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteSous_categorie/{id}")
public ResponseEntity<Sous_categorieDto> deleteSous_categorie(@PathVariable int id) {
	if(userRepo.existsById(id)) {
		Sous_categorie ab=userRepo.deleteById(id);
		Sous_categorieDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Sous_categorieDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Sous_categorieDto>(HttpStatus.NOT_FOUND);
}




}
