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

import com.test.microservices.dto.ParametresBatchDto;
import com.test.microservices.mappers.ParametresBatchDtoToParametresBatch;
import com.test.microservices.pojos.ParametresBatch;
import com.test.microservices.pojos.Pari_compositionElement;
import com.test.microservices.repositories.ParametresBatchRepository;
@RestController
public class ParametresBatchController {
	ParametresBatchRepository parametresBatchRepo;
	ParametresBatchDtoToParametresBatch mapper;
	public ParametresBatchController(ParametresBatchRepository repo,ParametresBatchDtoToParametresBatch m) {
		this.parametresBatchRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getParametresBatchByIdMongo/{id}")
public ResponseEntity<ParametresBatchDto> getParametresBatch( @PathVariable String id) {
	if(parametresBatchRepo.existsByIdMongo(id)) {
		ParametresBatch ab=parametresBatchRepo.findByIdMongo( id);
		ParametresBatchDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ParametresBatchDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ParametresBatchDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getParametresBatchById/{id}")
public ResponseEntity<ParametresBatchDto> getParametresBatch( @PathVariable int id) {
	if(parametresBatchRepo.existsById(id)) {
		ParametresBatch ab=parametresBatchRepo.findById( id);
		ParametresBatchDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ParametresBatchDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ParametresBatchDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllParametresBatchs")
public ResponseEntity<List<ParametresBatchDto>> getParametresBatch( ) {
	List<ParametresBatch> lab=parametresBatchRepo.findAll();
	List<ParametresBatchDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ParametresBatchDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getParametresBatchsActifs")
public ResponseEntity<List<ParametresBatchDto>> getParametresBatchActifs( ) {
	List<ParametresBatch> lab=parametresBatchRepo.findByActif(true);
	List<ParametresBatchDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ParametresBatchDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getParametresBatchsNonActifs")
public ResponseEntity<List<ParametresBatchDto>> getParametresBatchNonActifs( ) {
	List<ParametresBatch> lab=parametresBatchRepo.findByActif(false);
	List<ParametresBatchDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ParametresBatchDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addParametresBatch")
public ResponseEntity<ParametresBatchDto> addParametresBatch(@RequestBody ParametresBatchDto dto) {
	int max;
	try{
	Page<ParametresBatch> c2 =parametresBatchRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
	max=c2.getContent().get(0).getId();
	}catch (Exception e) {
		max=0;
	}
	if(max!=0) {
		List<ParametresBatch>liste=parametresBatchRepo.findAll();
		for (ParametresBatch p:liste) {
			
			p.setActif(false);
		}
		parametresBatchRepo.saveAll(liste);
	}
	System.out.println("Id max: "+max);
	ParametresBatch ab=mapper.dtoToObject(dto);
	dto.setId(max+1);
	ab.setId(max+1);
	ab.setActif(true);
	parametresBatchRepo.save(ab);
	return new ResponseEntity<ParametresBatchDto>(dto,HttpStatus.OK);
}
@PutMapping("/updateParametresBatch/{id}")
public ResponseEntity<ParametresBatchDto> updateParametresBatch(@PathVariable int id,@RequestBody ParametresBatchDto dto) {
		String idMongo=parametresBatchRepo.findById(id).getIdMongo();
		ParametresBatch ab=mapper.dtoToObject(dto);
		parametresBatchRepo.deleteById(idMongo);
		ab.setIdMongo(idMongo);
		parametresBatchRepo.save(ab);
		return new ResponseEntity<ParametresBatchDto>(dto,HttpStatus.OK);
	
}
@DeleteMapping("/deleteParametresBatch/{id}")
public ResponseEntity<ParametresBatchDto> deleteParametresBatch(@PathVariable int id) {
	if(parametresBatchRepo.existsById(id)) {
		ParametresBatch ab=parametresBatchRepo.deleteById(id);
		ParametresBatchDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ParametresBatchDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ParametresBatchDto>(HttpStatus.NOT_FOUND);
}

}
