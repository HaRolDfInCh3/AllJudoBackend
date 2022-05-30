package com.test.microservices.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
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
@GetMapping("/getNextEventsByCategorieAndAge/{age_id}/{cat_id}/{mois}/{annee}")
public ResponseEntity<List<EvenementDto>> getNextEventsByCategorieAndAge( @PathVariable int age_id,@PathVariable int cat_id,@PathVariable int mois,@PathVariable int annee) throws ParseException {
	String date_deb="01/"+(mois+1)+"/"+annee;  
	SimpleDateFormat pattern=new SimpleDateFormat("dd/MM/yyyy");
    Date date1=pattern.parse(date_deb);  
		Date date_fin=DateUtils.addMonths(date1, 2);
		date_fin.setDate(30);
		System.out.println("de "+pattern.format(date1) +" a "+pattern.format(date_fin));
		List<Evenement> ab=null;
		if(age_id==-1) {
			ab=objetRepo.findNextEventsByCat(age_id, cat_id, date1, date_fin);
		}if(cat_id==-1) {
			ab=objetRepo.findNextEventsByAge(age_id, cat_id, date1, date_fin);
		}else {
			ab=objetRepo.findNextEventsByCatAndAge(age_id, cat_id, date1, date_fin);

		}
		List<EvenementDto> dto=mapper.objectsToDtos(ab);
		return new ResponseEntity<List<EvenementDto>>(dto,HttpStatus.OK);
	
}
@GetMapping("/getNextEventsByTrimester/{mois}/{annee}")
public ResponseEntity<List<EvenementDto>> getNextEventsByTrimester(@PathVariable int mois,@PathVariable int annee) throws ParseException {
	
	
	String date_deb="01/"+(mois+1)+"/"+annee;  
	SimpleDateFormat pattern=new SimpleDateFormat("dd/MM/yyyy");
    Date date1=pattern.parse(date_deb);  
		Date date_fin=DateUtils.addMonths(date1, 2);
		date_fin.setDate(30);
		System.out.println("de "+pattern.format(date1) +" a "+pattern.format(date_fin));
		List<Evenement> ab=objetRepo.findNextEventsByTrimester( date1, date_fin,Sort.by(Sort.Direction.ASC, "DateDebut"));
		List<EvenementDto> dto=mapper.objectsToDtos(ab);
		return new ResponseEntity<List<EvenementDto>>(dto,HttpStatus.OK);
	
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
	Page<Evenement> c2 =objetRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
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
		ab.setId(max+1);
		objetRepo.save(ab);
		return new ResponseEntity<EvenementDto>(dto,HttpStatus.CREATED);
	
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
