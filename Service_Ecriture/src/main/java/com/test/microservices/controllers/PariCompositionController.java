package com.test.microservices.controllers;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.microservices.dto.Pari_compositionDto;
import com.test.microservices.dto.Pari_compositionElementDto;
import com.test.microservices.mappers.Pari_compositionDtoToPari_composition;
import com.test.microservices.mappers.Pari_compositionElementDtoToPari_compositionElement;
import com.test.microservices.pojos.Pari_composition;
import com.test.microservices.pojos.Pari_compositionElement;
import com.test.microservices.repositories.Pari_compositionElementRepository;
import com.test.microservices.repositories.Pari_compositionRepository;

@RestController
public class PariCompositionController {
	Pari_compositionRepository resultatRepo;
	
	Pari_compositionElementRepository pceR;
	Pari_compositionDtoToPari_composition mapper;
	Pari_compositionElementDtoToPari_compositionElement mapper2;
	public PariCompositionController(Pari_compositionElementDtoToPari_compositionElement m2,Pari_compositionElementRepository p,Pari_compositionRepository repo,Pari_compositionDtoToPari_composition m) {
		this.resultatRepo=repo;
		this.pceR=p;
		this.mapper2=m2;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getPari_compositionByIdMongo/{id}")
public ResponseEntity<Pari_compositionDto> getPari_composition( @PathVariable String id) {
	if(resultatRepo.existsByIdMongo(id)) {
		Pari_composition ab=resultatRepo.findByIdMongo( id);
		Pari_compositionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPari_compositionById/{id}")
public ResponseEntity<Pari_compositionDto> getPari_composition( @PathVariable int id) {
	if(resultatRepo.existsById(id)) {
		Pari_composition ab=resultatRepo.findById( id);
		Pari_compositionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllPari_compositions")
public ResponseEntity<List<Pari_compositionDto>> getPari_composition( ) {
	List<Pari_composition> lab=resultatRepo.findAll();
	List<Pari_compositionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Pari_compositionDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addPari_composition")
public ResponseEntity<Pari_compositionDto> addPari_composition(@RequestBody Pari_compositionDto dto) {
	Page<Pari_composition> c2 =resultatRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Pari_composition ab=mapper.dtoToObject(dto);
		ab.setId(max+1);
		dto.setId(max+1);
		resultatRepo.save(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updatePari_composition/{id}")
public ResponseEntity<Pari_compositionDto> updatePari_composition(@PathVariable int id,@RequestBody Pari_compositionDto dto) {
	System.out.println(dto);
	String idMongo=resultatRepo.findById(id).getIdMongo();
	
		Pari_composition ab=mapper.dtoToObject(dto);
		List<Pari_compositionElement>listpce=ab.getElements();
		pceR.saveAll(listpce);
		resultatRepo.deleteById(idMongo);
		ab.setIdMongo(idMongo);
		resultatRepo.save(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	
}
@PostMapping("/addPari_compositionElement")
public ResponseEntity<Pari_compositionElementDto> addPari_compositionElement(@RequestBody Pari_compositionElementDto dto) {
	int max;
	try{
	Page<Pari_compositionElement> c2 =pceR.findByParicompositionid(dto.getParicompositionid(),PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
	max=c2.getContent().get(0).getId();
	}catch (Exception e) {
		max=0;
	}
	
	System.out.println("Id max: "+max);
		Pari_compositionElement ab=mapper2.dtoToObject(dto);
		ab.setId(max+1);
		dto.setId(max+1);
		pceR.save(ab);
		Pari_composition pc=resultatRepo.findById(dto.getParicompositionid());
	List<Pari_compositionElement>elements=pc.getElements();
	elements.add(ab);
	pc.setElements(elements);
	resultatRepo.save(pc);
		return new ResponseEntity<Pari_compositionElementDto>(dto,HttpStatus.CREATED);
	
}
@DeleteMapping("/deletePari_composition/{id}")
public ResponseEntity<Pari_compositionDto> deletePari_composition(@PathVariable int id) {
	if(resultatRepo.existsById(id)) {
		Pari_composition ab=resultatRepo.deleteById(id);
		Pari_compositionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Pari_compositionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Pari_compositionDto>(HttpStatus.NOT_FOUND);
}

@PostMapping("/uploadCompo")
public void handleFileUpload(@RequestPart(value="files[]", required = true) final MultipartFile[] files) {
	System.out.println("Nom original: " + files[0].getOriginalFilename() + "!");
	System.out.println("Type de contenu: " + files[0].getContentType());
	System.out.println("Nom: " + files[0].getName());
	System.out.println("Taille: " + files[0].getSize());
	String content;
	try {
		content = new String(files[0].getBytes(), StandardCharsets.UTF_8);
		System.out.println("Contenu" + content);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}

}
