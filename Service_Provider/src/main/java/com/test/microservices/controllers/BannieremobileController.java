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

import com.test.microservices.dto.BannieremobileDto;
import com.test.microservices.mappers.BannieremobileDtoToBannieremobile;
import com.test.microservices.pojos.Bannieremobile;
import com.test.microservices.repositories.BmRepository;
@RestController
public class BannieremobileController {
	BmRepository bmRepo;
	BannieremobileDtoToBannieremobile mapper;
	public BannieremobileController(BmRepository repo,BannieremobileDtoToBannieremobile m) {
		this.bmRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getBannieremobileByIdMongo/{id}")
public ResponseEntity<BannieremobileDto> getBannieremobile( @PathVariable String id) {
	if(bmRepo.existsByIdMongo(id)) {
		Bannieremobile ab=bmRepo.findByIdMongo( id);
		BannieremobileDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannieremobileDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannieremobileDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getBannieremobileById/{id}")
public ResponseEntity<BannieremobileDto> getBannieremobile( @PathVariable int id) {
	if(bmRepo.existsById(id)) {
		Bannieremobile ab=bmRepo.findById( id);
		BannieremobileDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannieremobileDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannieremobileDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllBannieremobiles")
public ResponseEntity<List<BannieremobileDto>> getBannieremobile( ) {
	List<Bannieremobile> lab=bmRepo.findAll();
	List<BannieremobileDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<BannieremobileDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addBannieremobile")
public ResponseEntity<BannieremobileDto> addBannieremobile(@RequestBody BannieremobileDto dto) {
	if(!bmRepo.existsById(dto.getId())) {
		Bannieremobile ab=mapper.dtoToObject(dto);
		bmRepo.save(ab);
		return new ResponseEntity<BannieremobileDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<BannieremobileDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateBannieremobile/{id}")
public ResponseEntity<BannieremobileDto> updateBannieremobile(@PathVariable int id,@RequestBody BannieremobileDto dto) {
	if(bmRepo.existsById(id)) {
		Bannieremobile ab=mapper.dtoToObject(dto);
		bmRepo.save(ab);
		return new ResponseEntity<BannieremobileDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannieremobileDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteBannieremobile/{id}")
public ResponseEntity<BannieremobileDto> deleteBannieremobile(@PathVariable int id) {
	if(bmRepo.existsById(id)) {
		Bannieremobile ab=bmRepo.deleteById(id);
		BannieremobileDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannieremobileDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannieremobileDto>(HttpStatus.NOT_FOUND);
}

}
