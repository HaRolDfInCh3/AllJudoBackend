package com.test.microservices.controllers;

import java.util.List;

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

import com.test.microservices.dto.EvenementDto;
import com.test.microservices.mappers.EvenementDtoToEvenement;
import com.test.microservices.pojos.Evcategorieage;
import com.test.microservices.pojos.Evcategorieevenement;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.pojos.Pays;
import com.test.microservices.repositories.EvcategorieageRepository;
import com.test.microservices.repositories.EvcategorieevenementRepository;
import com.test.microservices.repositories.EvenementRepository;
import com.test.microservices.repositories.PaysRepository;
@RestController
public class EvenementController {
	EvenementDtoToEvenement mapper;
	EvenementRepository objetRepo;
	EvcategorieageRepository evcatageRepo;
	EvcategorieevenementRepository evcatevRepo;
	PaysRepository paysRepo;
	public EvenementController(PaysRepository p,EvcategorieageRepository eca,EvcategorieevenementRepository ece,EvenementRepository repo,EvenementDtoToEvenement m) {
		this.objetRepo=repo;
		this.paysRepo=p;
		this.evcatageRepo=eca;
		this.evcatevRepo=ece;
		this.mapper=m;
	}
@GetMapping("/getEvenementByIdMongo/{id}")
public ResponseEntity<EvenementDto> getEvenement( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Evenement ab=objetRepo.findByIdMongo( id);
		EvenementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEvenementById/{id}")
public ResponseEntity<EvenementDto> getEvenement( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenement ab=objetRepo.findById( id);
		EvenementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvenements")
public ResponseEntity<List<EvenementDto>> getEvenement( ) {
	List<Evenement> lab=objetRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllEvenementsDesc")
public ResponseEntity<List<EvenementDto>> getEvenementDesc( ) {
	List<Evenement> lab=objetRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<EvenementDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvenementDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addEvenement")
public ResponseEntity<EvenementDto> addEvenement(@RequestBody EvenementDto dto) {
	System.out.println("Recu: "+dto);
	//on suppose que les elements referencés existent deja
	if(!objetRepo.existsById(dto.getId())) {
		int evcatevid=dto.getCategorieID();
		int evcatageid=dto.getCategorieageID();
		String paysid=dto.getPaysID();
		Evenement ab=mapper.dtoToObject(dto);
		if(paysRepo.existsById(Integer.parseInt(paysid))) {
			Pays p=paysRepo.findById(Integer.parseInt(paysid));
			ab.setPays2(p);
		}
		if(evcatageRepo.existsById(evcatageid)) {
			Evcategorieage eca=evcatageRepo.findById(evcatageid);
			ab.setEvcategorieage2(eca);
		}
		if(evcatevRepo.existsById(evcatevid)) {
			Evcategorieevenement ece=evcatevRepo.findById(evcatevid);
			ab.setEvcategorieevenement2(ece);
		}
		
		objetRepo.save(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateEvenement/{id}")
public ResponseEntity<EvenementDto> updateEvenement(@PathVariable int id,@RequestBody EvenementDto dto) {
	Evenement ancien=objetRepo.findById(id);
	String idMongo=ancien.getIdMongo();
	int evcatevid=dto.getCategorieID();
	int evcatageid=dto.getCategorieageID();
	String paysid=dto.getPaysID();
	Evenement ab=mapper.dtoToObject(dto);
	if(paysRepo.existsById(Integer.parseInt(paysid))) {
		Pays p=paysRepo.findById(Integer.parseInt(paysid));
		ab.setPays2(p);
	}
	if(evcatageRepo.existsById(evcatageid)) {
		Evcategorieage eca=evcatageRepo.findById(evcatageid);
		ab.setEvcategorieage2(eca);
	}
	if(evcatevRepo.existsById(evcatevid)) {
		Evcategorieevenement ece=evcatevRepo.findById(evcatevid);
		ab.setEvcategorieevenement2(ece);
	}
	ab.setIdMongo(idMongo);
	objetRepo.deleteById(idMongo);
	objetRepo.save(ab);
	return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
}
@DeleteMapping("/deleteEvenement/{id}")
public ResponseEntity<EvenementDto> deleteEvenement(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evenement ab=objetRepo.deleteById(id);
		EvenementDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvenementDto>(HttpStatus.NOT_FOUND);
}

}
