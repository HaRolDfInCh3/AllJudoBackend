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

import com.test.microservices.dto.Champion_admin_externe_palmaresDto;
import com.test.microservices.mappers.Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares;
import com.test.microservices.pojos.Champion;
import com.test.microservices.pojos.Champion_admin_externe_palmares;
import com.test.microservices.pojos.Departement;
import com.test.microservices.pojos.Evcategorieage;
import com.test.microservices.pojos.Region;
import com.test.microservices.repositories.ChampionAdminExternePalmaresRepository;
import com.test.microservices.repositories.DepartementRepository;
import com.test.microservices.repositories.EvcategorieageRepository;
import com.test.microservices.repositories.RegionRepository;
@RestController
public class Champion_admin_externe_palmaresController {
	ChampionAdminExternePalmaresRepository cAEPRepo;
	DepartementRepository depR;
	RegionRepository rR;
	EvcategorieageRepository ecaR;
	Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares mapper;
	public Champion_admin_externe_palmaresController(DepartementRepository d,RegionRepository r,EvcategorieageRepository e,ChampionAdminExternePalmaresRepository repo,Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares m) {
		this.cAEPRepo=repo;
		this.depR=d;
		this.rR=r;
		this.ecaR=e;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampion_admin_externe_palmaresByIdMongo/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> getChampion_admin_externe_palmares( @PathVariable String id) {
	if(cAEPRepo.existsByIdMongo(id)) {
		Champion_admin_externe_palmares ab=cAEPRepo.findByIdMongo( id);
		Champion_admin_externe_palmaresDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampion_admin_externe_palmaresById/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> getChampion_admin_externe_palmares( @PathVariable int id) {
	if(cAEPRepo.existsById(id)) {
		Champion_admin_externe_palmares ab=cAEPRepo.findById( id);
		Champion_admin_externe_palmaresDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampion_admin_externe_palmaress")
public ResponseEntity<List<Champion_admin_externe_palmaresDto>> getChampion_admin_externe_palmares( ) {
	List<Champion_admin_externe_palmares> lab=cAEPRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<Champion_admin_externe_palmaresDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Champion_admin_externe_palmaresDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllChampion_admin_externe_palmaressDesc")
public ResponseEntity<List<Champion_admin_externe_palmaresDto>> getChampion_admin_externe_palmaresDesc( ) {
	List<Champion_admin_externe_palmares> lab=cAEPRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<Champion_admin_externe_palmaresDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<Champion_admin_externe_palmaresDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion_admin_externe_palmares")
public ResponseEntity<Champion_admin_externe_palmaresDto> addChampion_admin_externe_palmares(@RequestBody Champion_admin_externe_palmaresDto dto) {
	Page<Champion_admin_externe_palmares> c2 =cAEPRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);	
	int depid=dto.getCompetitionDepID();
	int regid=dto.getCompetitionRegID();
	int catageid=dto.getCategorieAge();
	Champion_admin_externe_palmares ab=mapper.dtoToObject(dto);
	if(depR.existsById(depid)) {
		Departement d=depR.findById(depid);
		ab.setDepartement2(d);
	}
	if(rR.existsById(regid)) {
		Region r=rR.findById(regid);
		ab.setRegion2(r);
	}
	if(ecaR.existsById(catageid)) {
		Evcategorieage cat=ecaR.findById(catageid);
		ab.setEvcategorieage2(cat);
	}
	
		
		cAEPRepo.save(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateChampion_admin_externe_palmares/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> updateChampion_admin_externe_palmares(@PathVariable int id,@RequestBody Champion_admin_externe_palmaresDto dto) {
	
	Champion_admin_externe_palmares ancien=cAEPRepo.findById(id);
	String idMongo=ancien.getIdMongo();
		Champion_admin_externe_palmares ab=mapper.dtoToObject(dto);
		int depid=dto.getCompetitionDepID();
		int regid=dto.getCompetitionRegID();
		int catageid=dto.getCategorieAge();
		if(depR.existsById(depid)) {
			Departement d=depR.findById(depid);
			ab.setDepartement2(d);
		}
		if(rR.existsById(regid)) {
			Region r=rR.findById(regid);
			ab.setRegion2(r);
		}
		if(ecaR.existsById(catageid)) {
			Evcategorieage cat=ecaR.findById(catageid);
			ab.setEvcategorieage2(cat);
		}
		ab.setIdMongo(idMongo);
		cAEPRepo.deleteById(idMongo);
		cAEPRepo.save(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
@DeleteMapping("/deleteChampion_admin_externe_palmares/{id}")
public ResponseEntity<Champion_admin_externe_palmaresDto> deleteChampion_admin_externe_palmares(@PathVariable int id) {
	if(cAEPRepo.existsById(id)) {
		Champion_admin_externe_palmares ab=cAEPRepo.deleteById(id);
		Champion_admin_externe_palmaresDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<Champion_admin_externe_palmaresDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<Champion_admin_externe_palmaresDto>(HttpStatus.NOT_FOUND);
}


}
