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

import com.test.microservices.dto.ChampionDto;
import com.test.microservices.mappers.ChampionDtoToChampion;
import com.test.microservices.pojos.Champion;
import com.test.microservices.pojos.Club;
import com.test.microservices.pojos.Pays;
import com.test.microservices.repositories.ChampionRepository;
import com.test.microservices.repositories.ClubRepository;
import com.test.microservices.repositories.PaysRepository;

@RestController
public class ChampionController {
	ChampionDtoToChampion mapper;
	ChampionRepository championRepo;
	ClubRepository clubR;
	PaysRepository paysR;
	public ChampionController(PaysRepository p,ClubRepository c,ChampionRepository repo,ChampionDtoToChampion m) {
		this.championRepo=repo;
		this.mapper=m;
		this.paysR=p;
		this.clubR=c;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampionByIdMongo/{id}")
public ResponseEntity<ChampionDto> getChampion( @PathVariable String id) {
	if(championRepo.existsByIdMongo(id)) {
		Champion ab=championRepo.findByIdMongo( id);
		ChampionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampionById/{id}")
public ResponseEntity<ChampionDto> getChampion( @PathVariable int id) {
	if(championRepo.existsById(id)) {
		Champion ab=championRepo.findById( id);
		ChampionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampions")
public ResponseEntity<List<ChampionDto>> getChampion( ) {
	List<Champion> lab=championRepo.findAll();
	List<ChampionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ChampionDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllChampionsByNameStart/{premierelettre}")
public ResponseEntity<List<ChampionDto>> getChampionByNameStart(@PathVariable String premierelettre ) {
	String regex="^"+premierelettre+".*";
	System.out.println(regex);
	List<Champion> lab=championRepo.findAllByNameStart(regex);
	List<ChampionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ChampionDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion")
public ResponseEntity<ChampionDto> addChampion(@RequestBody ChampionDto dto) {
		Page<Champion> c2 =championRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
		int max=c2.getContent().get(0).getId();
		System.out.println("Id max: "+max);	
		String clubName=dto.getClubs();
		String paysId=dto.getPaysID();
		String nvpaysId=dto.getNvPaysID();
		Champion ab=mapper.dtoToObject(dto);
		if(clubName!=null) {
			List<Club> c=clubR.findByClub(clubName);
			ab.setClub2(c.get(0));
		}
		if(paysId!=null) {
			Pays p=paysR.findByAbreviation(paysId);
			ab.setPays2(p);
		}
		if(nvpaysId!=null) {
			Pays p2=paysR.findByAbreviation(nvpaysId);
			ab.setPays2(p2);
		}
		ab.setId(max+1);	
		
		championRepo.save(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.CREATED);
}
@PutMapping("/updateChampion/{id}")
public ResponseEntity<ChampionDto> updateChampion(@PathVariable int id,@RequestBody ChampionDto dto) {
	if(championRepo.existsById(id)) {
		Champion ancien=championRepo.findById(id);
		String idMongo=ancien.getIdMongo();
		String clubName=dto.getClubs();
		String paysId=dto.getPaysID();
		String nvpaysId=dto.getNvPaysID();
		Champion ab=mapper.dtoToObject(dto);
		if(clubName!=null) {
			List<Club> c=clubR.findByClub(clubName);
			ab.setClub2(c.get(0));
		}
		if(paysId!=null) {
			Pays p=paysR.findByAbreviation(paysId);
			ab.setPays2(p);
		}
		if(nvpaysId!=null) {
			Pays p2=paysR.findByAbreviation(nvpaysId);
			ab.setPays2(p2);
		}
		ab.setIdMongo(idMongo);
		championRepo.deleteById(idMongo);
		championRepo.save(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteChampion/{id}")
public ResponseEntity<ChampionDto> deleteChampion(@PathVariable int id) {
	if(championRepo.existsById(id)) {
		Champion ab=championRepo.deleteById(id);
		ChampionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}


}
