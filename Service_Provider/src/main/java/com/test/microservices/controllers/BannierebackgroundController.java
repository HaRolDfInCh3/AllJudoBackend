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

import com.test.microservices.dto.BannierebackgroundDto;
import com.test.microservices.mappers.BannierebackgroundDtoToannierebackground;
import com.test.microservices.pojos.Bannierebackground;
import com.test.microservices.repositories.BbRepository;

@RestController
public class BannierebackgroundController {
	BbRepository bbRepo;
	BannierebackgroundDtoToannierebackground mapper;
	public BannierebackgroundController(BbRepository repo,BannierebackgroundDtoToannierebackground m) {
		this.bbRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getBannierebackgroundByIdMongo/{id}")
public ResponseEntity<BannierebackgroundDto> getBannierebackground( @PathVariable String id) {
	if(bbRepo.existsByIdMongo(id)) {
		Bannierebackground ab=bbRepo.findByIdMongo( id);
		BannierebackgroundDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannierebackgroundDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierebackgroundDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getBannierebackgroundById/{id}")
public ResponseEntity<BannierebackgroundDto> getBannierebackground( @PathVariable int id) {
	if(bbRepo.existsById(id)) {
		Bannierebackground ab=bbRepo.findById( id);
		BannierebackgroundDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannierebackgroundDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierebackgroundDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllBannierebackgrounds")
public ResponseEntity<List<BannierebackgroundDto>> getBannierebackground( ) {
	List<Bannierebackground> lab=bbRepo.findAll();
	List<BannierebackgroundDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<BannierebackgroundDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addBannierebackground")
public ResponseEntity<BannierebackgroundDto> addBannierebackground(@RequestBody BannierebackgroundDto dto) {
	if(!bbRepo.existsById(dto.getId())) {
		Bannierebackground ab=mapper.dtoToObject(dto);
		bbRepo.save(ab);
		return new ResponseEntity<BannierebackgroundDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<BannierebackgroundDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateBannierebackground/{id}")
public ResponseEntity<BannierebackgroundDto> updateBannierebackground(@PathVariable int id,@RequestBody BannierebackgroundDto dto) {
	if(bbRepo.existsById(id)) {
		Bannierebackground ab=mapper.dtoToObject(dto);
		bbRepo.save(ab);
		return new ResponseEntity<BannierebackgroundDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierebackgroundDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteBannierebackground/{id}")
public ResponseEntity<BannierebackgroundDto> deleteBannierebackground(@PathVariable int id) {
	if(bbRepo.existsById(id)) {
		Bannierebackground ab=bbRepo.deleteById(id);
		BannierebackgroundDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannierebackgroundDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierebackgroundDto>(HttpStatus.NOT_FOUND);
}

}
