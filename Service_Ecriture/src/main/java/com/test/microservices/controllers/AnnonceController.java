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

import com.test.microservices.dto.AnnonceDto;
import com.test.microservices.mappers.AnnonceDtoToAnnonce;
import com.test.microservices.pojos.Annonce;
import com.test.microservices.pojos.Sous_categorie;
import com.test.microservices.pojos.Video;
import com.test.microservices.repositories.AnnonceRepository;
import com.test.microservices.repositories.Sous_categorieRepository;

@RestController
public class AnnonceController {
	AnnonceRepository annonceRepo;
	AnnonceDtoToAnnonce mapper;
	Sous_categorieRepository sscatR;
	public AnnonceController(Sous_categorieRepository s,AnnonceRepository repo,AnnonceDtoToAnnonce m) {
		this.annonceRepo=repo;
		this.mapper=m;
		this.sscatR=s;
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
	Page<Annonce> c2 =annonceRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Annonce ab=mapper.dtoToObject(dto);
		int sscat=dto.getSous_categorie_ID();
		if(sscatR.existsById(sscat)){
			Sous_categorie sous=sscatR.findById(sscat);
			ab.setSous_categorie2(sous);
		}
		ab.setId(max+1);
		dto.setId(max+1);
		annonceRepo.save(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateAnnonce/{id}")
public ResponseEntity<AnnonceDto> updateAnnonce(@PathVariable int id,@RequestBody AnnonceDto dto) {
		String idMongo=annonceRepo.findById(id).getIdMongo();
		Annonce ab=mapper.dtoToObject(dto);
		int sscat=dto.getSous_categorie_ID();
		if(sscatR.existsById(sscat)){
			Sous_categorie sous=sscatR.findById(sscat);
			ab.setSous_categorie2(sous);
		}
		annonceRepo.deleteById(idMongo);
		ab.setIdMongo(idMongo);
		annonceRepo.save(ab);
		return new ResponseEntity<AnnonceDto>(dto,HttpStatus.OK);
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
