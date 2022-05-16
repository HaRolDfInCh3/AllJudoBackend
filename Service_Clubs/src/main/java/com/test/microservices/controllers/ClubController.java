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

import com.test.microservices.dto.ClubDto;
import com.test.microservices.mappers.ClubDtoToClub;
import com.test.microservices.pojos.Club;
import com.test.microservices.pojos.Departement;
import com.test.microservices.pojos.Pays;
import com.test.microservices.repositories.ClubRepository;
import com.test.microservices.repositories.DepartementRepository;
import com.test.microservices.repositories.PaysRepository;
@RestController
public class ClubController {
	ClubDtoToClub mapper;
	ClubRepository clubRepo;
	PaysRepository paysR;
	DepartementRepository depR;
	public ClubController(PaysRepository p,DepartementRepository d,ClubRepository repo,ClubDtoToClub m) {
		this.clubRepo=repo;
		this.depR=d;
		this.paysR=p;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getClubByIdMongo/{id}")
public ResponseEntity<ClubDto> getClub( @PathVariable String id) {
	if(clubRepo.existsByIdMongo(id)) {
		Club ab=clubRepo.findByIdMongo( id);
		ClubDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ClubDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ClubDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getClubById/{id}")
public ResponseEntity<ClubDto> getClub( @PathVariable int id) {
	if(clubRepo.existsById(id)) {
		Club ab=clubRepo.findById( id);
		ClubDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ClubDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ClubDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllClubs")
public ResponseEntity<List<ClubDto>> getClub( ) {
	List<Club> lab=clubRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<ClubDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ClubDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllClubsDesc")
public ResponseEntity<List<ClubDto>> getClubDesc( ) {
	List<Club> lab=clubRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<ClubDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ClubDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addClub")
public ResponseEntity<ClubDto> addClub(@RequestBody ClubDto dto) {
	Page<Club> c2 =clubRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Club ab=mapper.dtoToObject(dto);
		String paysid=dto.getPays();
		String depid=dto.getDepartement();
		if(depR.existsByNomDepartement(depid)) {
			Departement dep=depR.findByNomDepartement(depid);
			ab.setDepartement2(dep);
		}
		if(paysR.existsByAbreviation(paysid)) {
			Pays pays=paysR.findByAbreviation(paysid);
			ab.setPays2(pays);
		}
		ab.setId(max+1);
		clubRepo.save(ab);
		return new ResponseEntity<ClubDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateClub/{id}")
public ResponseEntity<ClubDto> updateClub(@PathVariable int id,@RequestBody ClubDto dto) {
		Club ancien=clubRepo.findById(id);
		String idMongo=ancien.getIdMongo();
		String paysid=dto.getPays();
		String depid=dto.getDepartement();
		Club ab=mapper.dtoToObject(dto);
		if(depR.existsByNomDepartement(depid)) {
			Departement dep=depR.findByNomDepartement(depid);
			ab.setDepartement2(dep);
		}
		if(paysR.existsByAbreviation(paysid)) {
			Pays pays=paysR.findByAbreviation(paysid);
			ab.setPays2(pays);
		}
		
		ab.setIdMongo(idMongo);
		clubRepo.deleteById(idMongo);
		clubRepo.save(ab);
		return new ResponseEntity<ClubDto>(dto,HttpStatus.OK);
	
}
@DeleteMapping("/deleteClub/{id}")
public ResponseEntity<ClubDto> deleteClub(@PathVariable int id) {
	if(clubRepo.existsById(id)) {
		Club ab=clubRepo.deleteById(id);
		ClubDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ClubDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ClubDto>(HttpStatus.NOT_FOUND);
}



}
