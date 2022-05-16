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

import com.test.microservices.dto.OptionDto;
import com.test.microservices.mappers.OptionDtoToOption;
import com.test.microservices.pojos.Option;
import com.test.microservices.pojos.Option;
import com.test.microservices.repositories.OptionsRepository;

@RestController
public class OptionsController {
	OptionsRepository optionsRepo;
	OptionDtoToOption mapper;
	public OptionsController(OptionsRepository repo,OptionDtoToOption m) {
		this.optionsRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getOptionByIdMongo/{id}")
public ResponseEntity<OptionDto> getOption( @PathVariable String id) {
	if(optionsRepo.existsByIdMongo(id)) {
		Option ab=optionsRepo.findByIdMongo( id);
		OptionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<OptionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<OptionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getOptionById/{id}")
public ResponseEntity<OptionDto> getOption( @PathVariable int id) {
	if(optionsRepo.existsById(id)) {
		Option ab=optionsRepo.findById( id);
		OptionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<OptionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<OptionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllOptions")
public ResponseEntity<List<OptionDto>> getOption( ) {
	List<Option> lab=optionsRepo.findAll();
	List<OptionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<OptionDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addOption")
public ResponseEntity<OptionDto> addOption(@RequestBody OptionDto dto) {
	if(!optionsRepo.existsById(dto.getId())) {
		Option ab=mapper.dtoToObject(dto);
		optionsRepo.save(ab);
		return new ResponseEntity<OptionDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<OptionDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateOption/{id}")
public ResponseEntity<OptionDto> updateOption(@PathVariable int id,@RequestBody OptionDto dto) {
	if(optionsRepo.existsById(id)) {
		Option ab=mapper.dtoToObject(dto);
		optionsRepo.save(ab);
		return new ResponseEntity<OptionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<OptionDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteOption/{id}")
public ResponseEntity<OptionDto> deleteOption(@PathVariable int id) {
	if(optionsRepo.existsById(id)) {
		Option ab=optionsRepo.deleteById(id);
		OptionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<OptionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<OptionDto>(HttpStatus.NOT_FOUND);
}

}
