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

import com.test.microservices.dto.Article_imgDto;
import com.test.microservices.mappers.Article_imgDtoToArticle_img;
import com.test.microservices.pojos.Article_img;
import com.test.microservices.repositories.Article_imgRepository;

@RestController
public class Article_imgController {
	Article_imgRepository article_imgRepo;
	Article_imgDtoToArticle_img mapper;
	public Article_imgController(Article_imgRepository repo,Article_imgDtoToArticle_img m) {
		this.article_imgRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getArticle_imgByIdMongo/{id}")
public ResponseEntity<Article_imgDto> getArticle_img( @PathVariable String id) {
	if(article_imgRepo.existsByIdMongo(id)) {
		Article_img ab=article_imgRepo.findByIdMongo( id);
		Article_imgDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Article_imgDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Article_imgDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getArticle_imgById/{id}")
public ResponseEntity<Article_imgDto> getArticle_img( @PathVariable int id) {
	if(article_imgRepo.existsById(id)) {
		Article_img ab=article_imgRepo.findById( id);
		Article_imgDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Article_imgDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Article_imgDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllArticle_imgs")
public ResponseEntity<List<Article_imgDto>> getArticle_img( ) {
	List<Article_img> lab=article_imgRepo.findAll();
	List<Article_imgDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Article_imgDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addArticle_img")
public ResponseEntity<Article_imgDto> addArticle_img(@RequestBody Article_imgDto dto) {
	if(!article_imgRepo.existsById(dto.getId())) {
		Article_img ab=mapper.dtoToObject(dto);
		article_imgRepo.save(ab);
		return new ResponseEntity<Article_imgDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Article_imgDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateArticle_img/{id}")
public ResponseEntity<Article_imgDto> updateArticle_img(@PathVariable int id,@RequestBody Article_imgDto dto) {
	if(article_imgRepo.existsById(id)) {
		Article_img ab=mapper.dtoToObject(dto);		
		article_imgRepo.save(ab);
		
		return new ResponseEntity<Article_imgDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Article_imgDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteArticle_img/{id}")
public ResponseEntity<Article_imgDto> deleteArticle_img(@PathVariable int id) {
	if(article_imgRepo.existsById(id)) {
		Article_img ab=article_imgRepo.deleteById(id);
		Article_imgDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Article_imgDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Article_imgDto>(HttpStatus.NOT_FOUND);
}


}
