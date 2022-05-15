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

import com.test.microservices.dto.RegionDto;
import com.test.microservices.mappers.RegionDtoToRegion;
import com.test.microservices.pojos.Region;
import com.test.microservices.repositories.RegionRepository;

@RestController
public class RegionController {
	RegionRepository regionRepo;
	RegionDtoToRegion mapper;
	public RegionController(RegionRepository repo,RegionDtoToRegion m) {
		this.regionRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getRegionByIdMongo/{id}")
public ResponseEntity<RegionDto> getRegion( @PathVariable String id) {
	if(regionRepo.existsByIdMongo(id)) {
		Region ab=regionRepo.findByIdMongo( id);
		RegionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<RegionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RegionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getRegionById/{id}")
public ResponseEntity<RegionDto> getRegion( @PathVariable int id) {
	if(regionRepo.existsById(id)) {
		Region ab=regionRepo.findById( id);
		RegionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<RegionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RegionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllRegions")
public ResponseEntity<List<RegionDto>> getRegion( ) {
	List<Region> lab=regionRepo.findAll();
	List<RegionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<RegionDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addRegion")
public ResponseEntity<RegionDto> addRegion(@RequestBody RegionDto dto) {
	if(!regionRepo.existsById(dto.getId())) {
		Region ab=mapper.dtoToObject(dto);
		regionRepo.save(ab);
		return new ResponseEntity<RegionDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<RegionDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateRegion/{id}")
public ResponseEntity<RegionDto> updateRegion(@PathVariable int id,@RequestBody RegionDto dto) {
	if(regionRepo.existsById(id)) {
		Region ab=mapper.dtoToObject(dto);
		regionRepo.save(ab);
		return new ResponseEntity<RegionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RegionDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteRegion/{id}")
public ResponseEntity<RegionDto> deleteRegion(@PathVariable int id) {
	if(regionRepo.existsById(id)) {
		Region ab=regionRepo.deleteById(id);
		RegionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<RegionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RegionDto>(HttpStatus.NOT_FOUND);
}



}
