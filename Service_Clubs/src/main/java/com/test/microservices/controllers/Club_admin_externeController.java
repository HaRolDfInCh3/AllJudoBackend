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

import com.test.microservices.dto.Club_admin_externeDto;
import com.test.microservices.mappers.Club_admin_externeDtoToClub_admin_externe;
import com.test.microservices.pojos.Club;
import com.test.microservices.pojos.Club_admin_externe;
import com.test.microservices.pojos.User;
import com.test.microservices.repositories.ClubRepository;
import com.test.microservices.repositories.Club_admin_externeRepository;
import com.test.microservices.repositories.UserRepository;
@RestController
public class Club_admin_externeController {
	Club_admin_externeDtoToClub_admin_externe mapper;
	Club_admin_externeRepository club_admin_externeRepo;
	UserRepository userRepo;
	ClubRepository clubRepo;
	public Club_admin_externeController(ClubRepository c,UserRepository u,Club_admin_externeRepository repo,Club_admin_externeDtoToClub_admin_externe m) {
		this.club_admin_externeRepo=repo;
		this.mapper=m;
		this.userRepo=u;
		this.clubRepo=c;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getClub_admin_externeByIdMongo/{id}")
public ResponseEntity<Club_admin_externeDto> getClub_admin_externe( @PathVariable String id) {
	if(club_admin_externeRepo.existsByIdMongo(id)) {
		Club_admin_externe ab=club_admin_externeRepo.findByIdMongo( id);
		Club_admin_externeDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externeDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getClub_admin_externeById/{id}")
public ResponseEntity<Club_admin_externeDto> getClub_admin_externe( @PathVariable int id) {
	if(club_admin_externeRepo.existsById(id)) {
		Club_admin_externe ab=club_admin_externeRepo.findById( id);
		Club_admin_externeDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externeDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllClub_admin_externes")
public ResponseEntity<List<Club_admin_externeDto>> getClub_admin_externe( ) {
	List<Club_admin_externe> lab=club_admin_externeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	List<Club_admin_externeDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Club_admin_externeDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllClub_admin_externesDesc")
public ResponseEntity<List<Club_admin_externeDto>> getClub_admin_externeDesc( ) {
	List<Club_admin_externe> lab=club_admin_externeRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	List<Club_admin_externeDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Club_admin_externeDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addClub_admin_externe")
public ResponseEntity<Club_admin_externeDto> addClub_admin_externe(@RequestBody Club_admin_externeDto dto) {
	Page<Club_admin_externe> c2 =club_admin_externeRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Club_admin_externe ab=mapper.dtoToObject(dto);
		int uid=dto.getUser_id();
		int cid=dto.getClub_id();
		if(userRepo.existsById(uid)) {
			User u=userRepo.findById(uid);
			ab.setUser2(u);
		}
		if(clubRepo.existsById(cid)) {
			Club c=clubRepo.findById(cid);
			ab.setClub2(c);
		}
		ab.setId(max+1);
		club_admin_externeRepo.save(ab);
		return new ResponseEntity<Club_admin_externeDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateClub_admin_externe/{id}")
public ResponseEntity<Club_admin_externeDto> updateClub_admin_externe(@PathVariable int id,@RequestBody Club_admin_externeDto dto) {
	Club_admin_externe ancien=club_admin_externeRepo.findById(id);
	String idMongo=ancien.getIdMongo();
		Club_admin_externe ab=mapper.dtoToObject(dto);
		int uid=dto.getUser_id();
		int cid=dto.getClub_id();
		if(userRepo.existsById(uid)) {
			User u=userRepo.findById(uid);
			ab.setUser2(u);
		}
		if(clubRepo.existsById(cid)) {
			Club c=clubRepo.findById(cid);
			ab.setClub2(c);
		}
		ab.setIdMongo(idMongo);
		club_admin_externeRepo.deleteById(idMongo);
		club_admin_externeRepo.save(ab);
		return new ResponseEntity<Club_admin_externeDto>(dto,HttpStatus.OK);
}
@DeleteMapping("/deleteClub_admin_externe/{id}")
public ResponseEntity<Club_admin_externeDto> deleteClub_admin_externe(@PathVariable int id) {
	if(club_admin_externeRepo.existsById(id)) {
		Club_admin_externe ab=club_admin_externeRepo.deleteById(id);
		Club_admin_externeDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Club_admin_externeDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Club_admin_externeDto>(HttpStatus.NOT_FOUND);
}



}
