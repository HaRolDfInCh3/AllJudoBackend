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

import com.test.microservices.dto.BannierenewsletterDto;
import com.test.microservices.mappers.BannierenewsletterDtoToBannierenewsletter;
import com.test.microservices.pojos.Bannierenewsletter;
import com.test.microservices.repositories.BnRepository;

@RestController
public class BannierenewsletterController {
	BnRepository bnRepo;
	BannierenewsletterDtoToBannierenewsletter mapper;
	public BannierenewsletterController(BnRepository repo,BannierenewsletterDtoToBannierenewsletter m) {
		this.bnRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getBannierenewsletterByIdMongo/{id}")
public ResponseEntity<BannierenewsletterDto> getBannierenewsletter( @PathVariable String id) {
	if(bnRepo.existsByIdMongo(id)) {
		Bannierenewsletter ab=bnRepo.findByIdMongo( id);
		BannierenewsletterDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannierenewsletterDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierenewsletterDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getBannierenewsletterById/{id}")
public ResponseEntity<BannierenewsletterDto> getBannierenewsletter( @PathVariable int id) {
	if(bnRepo.existsById(id)) {
		Bannierenewsletter ab=bnRepo.findById( id);
		BannierenewsletterDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannierenewsletterDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierenewsletterDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllBannierenewsletters")
public ResponseEntity<List<BannierenewsletterDto>> getBannierenewsletter( ) {
	List<Bannierenewsletter> lab=bnRepo.findAll();
	List<BannierenewsletterDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<BannierenewsletterDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addBannierenewsletter")
public ResponseEntity<BannierenewsletterDto> addBannierenewsletter(@RequestBody BannierenewsletterDto dto) {
	if(!bnRepo.existsById(dto.getId())) {
		Bannierenewsletter ab=mapper.dtoToObject(dto);
		bnRepo.save(ab);
		
		return new ResponseEntity<BannierenewsletterDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<BannierenewsletterDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateBannierenewsletter/{id}")
public ResponseEntity<BannierenewsletterDto> updateBannierenewsletter(@PathVariable int id,@RequestBody BannierenewsletterDto dto) {
	if(bnRepo.existsById(id)) {
		Bannierenewsletter ab=mapper.dtoToObject(dto);
		bnRepo.save(ab);
		return new ResponseEntity<BannierenewsletterDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierenewsletterDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteBannierenewsletter/{id}")
public ResponseEntity<BannierenewsletterDto> deleteBannierenewsletter(@PathVariable int id) {
	if(bnRepo.existsById(id)) {
		Bannierenewsletter ab=bnRepo.deleteById(id);
		BannierenewsletterDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<BannierenewsletterDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<BannierenewsletterDto>(HttpStatus.NOT_FOUND);
}


}
