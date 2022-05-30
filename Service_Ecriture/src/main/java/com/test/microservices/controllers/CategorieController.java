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
	List<Categorie> lab=categorieRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<CategorieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<CategorieDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addCategorie")
public ResponseEntity<CategorieDto> addCategorie(@RequestBody CategorieDto dto) {
	Page<Categorie> c2 =categorieRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Categorie ab=mapper.dtoToObject(dto);
		
		dto.setId(max+1);
		ab.setId(max+1);
		categorieRepo.save(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateCategorie/{id}")
public ResponseEntity<CategorieDto> updateCategorie(@PathVariable int id,@RequestBody CategorieDto dto) {
	String idMongo=categorieRepo.findById(id).getIdMongo();
	
		Categorie ab=mapper.dtoToObject(dto);
		ab.setIdMongo(idMongo);
		categorieRepo.deleteById(idMongo);
		categorieRepo.save(ab);
		return new ResponseEntity<CategorieDto>(dto,HttpStatus.OK);
	
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
