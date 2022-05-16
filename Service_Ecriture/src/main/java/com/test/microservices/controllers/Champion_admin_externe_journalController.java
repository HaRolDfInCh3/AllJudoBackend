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

import com.test.microservices.dto.Champion_admin_externe_journalDto;
import com.test.microservices.mappers.Champion_admin_externe_journalDtoToChampion_admin_externe_journal;
import com.test.microservices.pojos.Champion_admin_externe_journal;
import com.test.microservices.repositories.ChampionAdminExterneJournalRepository;

@RestController
public class Champion_admin_externe_journalController {
	ChampionAdminExterneJournalRepository cAEJRepo;
	Champion_admin_externe_journalDtoToChampion_admin_externe_journal mapper;
	public Champion_admin_externe_journalController(ChampionAdminExterneJournalRepository repo,Champion_admin_externe_journalDtoToChampion_admin_externe_journal m) {
		this.cAEJRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampion_admin_externe_journalByIdMongo/{id}")
public ResponseEntity<Champion_admin_externe_journalDto> getChampion_admin_externe_journal( @PathVariable String id) {
	if(cAEJRepo.existsByIdMongo(id)) {
		Champion_admin_externe_journal ab=cAEJRepo.findByIdMongo( id);
		Champion_admin_externe_journalDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampion_admin_externe_journalById/{id}")
public ResponseEntity<Champion_admin_externe_journalDto> getChampion_admin_externe_journal( @PathVariable int id) {
	if(cAEJRepo.existsById(id)) {
		Champion_admin_externe_journal ab=cAEJRepo.findById( id);
		Champion_admin_externe_journalDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampion_admin_externe_journals")
public ResponseEntity<List<Champion_admin_externe_journalDto>> getChampion_admin_externe_journal( ) {
	List<Champion_admin_externe_journal> lab=cAEJRepo.findAll();
	List<Champion_admin_externe_journalDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Champion_admin_externe_journalDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion_admin_externe_journal")
public ResponseEntity<Champion_admin_externe_journalDto> addChampion_admin_externe_journal(@RequestBody Champion_admin_externe_journalDto dto) {
	if(!cAEJRepo.existsById(dto.getId())) {
		Champion_admin_externe_journal ab=mapper.dtoToObject(dto);
		cAEJRepo.save(ab);
		return new ResponseEntity<Champion_admin_externe_journalDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<Champion_admin_externe_journalDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateChampion_admin_externe_journal/{id}")
public ResponseEntity<Champion_admin_externe_journalDto> updateChampion_admin_externe_journal(@PathVariable int id,@RequestBody Champion_admin_externe_journalDto dto) {
	if(cAEJRepo.existsById(id)) {
		Champion_admin_externe_journal ab=mapper.dtoToObject(dto);
		cAEJRepo.save(ab);
		return new ResponseEntity<Champion_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteChampion_admin_externe_journal/{id}")
public ResponseEntity<Champion_admin_externe_journalDto> deleteChampion_admin_externe_journal(@PathVariable int id) {
	if(cAEJRepo.existsById(id)) {
		Champion_admin_externe_journal ab=cAEJRepo.deleteById(id);
		Champion_admin_externe_journalDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_journalDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_journalDto>(HttpStatus.NOT_FOUND);
}


}
