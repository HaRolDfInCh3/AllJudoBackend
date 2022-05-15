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

import com.test.microservices.dto.ArticleDto;
import com.test.microservices.mappers.ArticleDtoToArticle;
import com.test.microservices.pojos.Article;
import com.test.microservices.repositories.ArticleRepository;

@RestController
public class ArticleController {
	ArticleRepository articleRepo;
	ArticleDtoToArticle mapper;
	public ArticleController(ArticleRepository repo,ArticleDtoToArticle m) {
		this.articleRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getArticleByIdMongo/{id}")
public ResponseEntity<ArticleDto> getArticle( @PathVariable String id) {
	if(articleRepo.existsByIdMongo(id)) {
		Article ab=articleRepo.findByIdMongo( id);
		ArticleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ArticleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ArticleDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getArticleById/{id}")
public ResponseEntity<ArticleDto> getArticle( @PathVariable int id) {
	if(articleRepo.existsById(id)) {
		Article ab=articleRepo.findById( id);
		ArticleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ArticleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ArticleDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllArticles")
public ResponseEntity<List<ArticleDto>> getArticle( ) {
	List<Article> lab=articleRepo.findAll();
	List<ArticleDto>ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ArticleDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addArticle")
public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto dto) {
	if(!articleRepo.existsById(dto.getId())) {
		Article ab=mapper.dtoToObject(dto);
		articleRepo.save(ab);
		return new ResponseEntity<ArticleDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<ArticleDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateArticle/{id}")
public ResponseEntity<ArticleDto> updateArticle(@PathVariable int id,@RequestBody ArticleDto dto) {
	if(articleRepo.existsById(id)) {
		Article ab=mapper.dtoToObject(dto);
		articleRepo.save(ab);
		return new ResponseEntity<ArticleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ArticleDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteArticle/{id}")
public ResponseEntity<ArticleDto> deleteArticle(@PathVariable int id) {
	if(articleRepo.existsById(id)) {
		Article ab=articleRepo.deleteById(id);
		ArticleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ArticleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ArticleDto>(HttpStatus.NOT_FOUND);
}

}
