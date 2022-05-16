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

import com.test.microservices.dto.GalerieDto;
import com.test.microservices.mappers.GalerieDtoToGalerie;
import com.test.microservices.pojos.Admin;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.pojos.Galerie;
import com.test.microservices.repositories.AdminRepository;
import com.test.microservices.repositories.EvenementRepository;
import com.test.microservices.repositories.GalerieRepository;
@RestController
public class GalerieController {
	GalerieDtoToGalerie mapper;
	AdminRepository aRepo;
	EvenementRepository eRepo;
	GalerieRepository objetRepo;
	public GalerieController(AdminRepository a,EvenementRepository e,GalerieRepository repo,GalerieDtoToGalerie m) {
		this.objetRepo=repo;
		this.aRepo=a;
		this.eRepo=e;
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
	List<Galerie> lab=objetRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<GalerieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<GalerieDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllGaleriesDesc")
public ResponseEntity<List<GalerieDto>> getGalerieDesc( ) {
	List<Galerie> lab=objetRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<GalerieDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<GalerieDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addGalerie")
public ResponseEntity<GalerieDto> addGalerie(@RequestBody GalerieDto dto) {
	Page<Galerie> c2 =objetRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
	int eid=dto.getEvenement_id();
	String aname=dto.getAdmin();
	Galerie ab=mapper.dtoToObject(dto);
	if(eRepo.existsById(eid)) {
		Evenement e=eRepo.findById(eid);
		ab.setEvenement2(e);
	}
	if(aRepo.existsByLogin(aname)) {
		Admin a=aRepo.findByLogin(aname);
		ab.setAdmin2(a);
	}
		ab.setId(max+1);		
		objetRepo.save(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateGalerie/{id}")
public ResponseEntity<GalerieDto> updateGalerie(@PathVariable int id,@RequestBody GalerieDto dto) {
	String idMongo=objetRepo.findById(id).getIdMongo();
	int eid=dto.getEvenement_id();
	String aname=dto.getAdmin();
	Galerie ab=mapper.dtoToObject(dto);
	if(eRepo.existsById(eid)) {
		Evenement e=eRepo.findById(eid);
		ab.setEvenement2(e);
	}
	if(aRepo.existsByLogin(aname)) {
		Admin a=aRepo.findByLogin(aname);
		ab.setAdmin2(a);
	}
		objetRepo.deleteById(idMongo);
		ab.setIdMongo(idMongo);
		objetRepo.save(ab);
		return new ResponseEntity<GalerieDto>(dto,HttpStatus.OK);
	
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
