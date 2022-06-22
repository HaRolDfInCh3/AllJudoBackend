package com.test.microservices.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.microservices.dto.EvresultatDto;
import com.test.microservices.mappers.EvresultatDtoToEvresultat;
import com.test.microservices.pojos.Champion;
import com.test.microservices.pojos.Classement_Clubs;
import com.test.microservices.pojos.Classement_Pays;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.pojos.Evresultat;
import com.test.microservices.pojos.GroupementResultatClubs;
import com.test.microservices.pojos.GroupementResultatPays;
import com.test.microservices.pojos.Palmares;
import com.test.microservices.repositories.ChampionRepository;
import com.test.microservices.repositories.EvenementRepository;
import com.test.microservices.repositories.EvresultatRepository;
import com.test.microservices.services.Resultat;
import com.test.microservices.services.XLSXReader;

@RestController
public class EvresultatController {
	EvresultatDtoToEvresultat mapper;
	EvresultatRepository objetRepo;
	ChampionRepository cRepo;
	EvenementRepository eventRepo;
	public EvresultatController(EvenementRepository e,ChampionRepository cR,EvresultatRepository repo,EvresultatDtoToEvresultat m) {
		this.objetRepo=repo;
		this.mapper=m;
		this.eventRepo=e;
		this.cRepo=cR;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getEvresultatByIdMongo/{id}")
public ResponseEntity<EvresultatDto> getEvresultat( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Evresultat ab=objetRepo.findByIdMongo( id);
		EvresultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getEvresultatById/{id}")
public ResponseEntity<EvresultatDto> getEvresultat( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evresultat ab=objetRepo.findById( id);
		EvresultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getPalmaresById/{championid}")
public ResponseEntity<List<Palmares>> getPalmares( @PathVariable int championid) {
		List<Palmares> liste=objetRepo.aggregateBySample(championid);
		return new ResponseEntity<List<Palmares>>(liste,HttpStatus.OK);
}
@GetMapping("/getclassementChampionsParEvenementID/{eventid}")
public ResponseEntity<List<GroupementResultatPays>> getclassementChampionsParEvenementID( @PathVariable int eventid) {
		List<GroupementResultatPays> liste=objetRepo.getclassementChampionsParEvenementID(eventid);
		return new ResponseEntity<List<GroupementResultatPays>>(liste,HttpStatus.OK);
}
@GetMapping("/getclassementChampionsParClubAndEvenementID/{eventid}")
public ResponseEntity<List<GroupementResultatClubs>> getclassementChampionsParClubAndEvenementID( @PathVariable int eventid) {
		List<GroupementResultatClubs> liste=objetRepo.getclassementChampionsParClubAndEvenementID(eventid);
		return new ResponseEntity<List<GroupementResultatClubs>>(liste,HttpStatus.OK);
}
@GetMapping("/getClassementPaysParEvenementID/{eventid}")
public ResponseEntity<List<Classement_Pays>> getClassementPaysParEvenementID( @PathVariable int eventid) {
	List<Classement_Pays> liste=objetRepo.getClassementPaysParEvenementID(eventid);
		return new ResponseEntity<List<Classement_Pays>>(liste,HttpStatus.OK);
}
@GetMapping("/getClassementClubParEvenementID/{eventid}")
public ResponseEntity<List<Classement_Clubs>> getClassementClubParEvenementID( @PathVariable int eventid) {
	List<Classement_Clubs> liste=objetRepo.getClassementClubsParEvenementID(eventid);
		return new ResponseEntity<List<Classement_Clubs>>(liste,HttpStatus.OK);
}
@GetMapping("/getClassementPaysParEvenementIDetParSexe/{eventid}/{sexe}")
public ResponseEntity<List<Classement_Pays>> getClassementPaysParEvenementIDetParSexe( @PathVariable int eventid,@PathVariable String sexe) {
	List<Classement_Pays> liste=objetRepo.getClassementPaysParEvenementIDetParSexe(eventid,sexe);
		return new ResponseEntity<List<Classement_Pays>>(liste,HttpStatus.OK);
}
@GetMapping("/getAllEvresultats")
public ResponseEntity<List<EvresultatDto>> getEvresultat( ) {
	List<Evresultat> lab=objetRepo.findAll();
	List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllEvresultatsByEventId/{id}")
public ResponseEntity<List<EvresultatDto>> getEvresultatByEventId(@PathVariable int id ) {
	if(objetRepo.existsByEvenementID(id)) {
		List<Evresultat> lab=objetRepo.findAllByEvenementID(id);
		List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
	}
	return new ResponseEntity<List<EvresultatDto>>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllEvresultatsByChampionId/{id}")
public ResponseEntity<List<EvresultatDto>> getEvresultatByChampionId(@PathVariable int id ) {
	
		List<Evresultat> lab=objetRepo.findAllByChampionID(id);
		List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
	
}
@GetMapping("/getAllEvresultatsByChampionIdDesc/{id}")
public ResponseEntity<List<EvresultatDto>> getEvresultatByChampionIdDesc(@PathVariable int id ) {
	
		List<Evresultat> lab=objetRepo.findAllResultatsDesc(id,Sort.by(Sort.Direction.DESC, "evenement2.DateDebut"));
		List<EvresultatDto> ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<EvresultatDto>>(ldto,HttpStatus.OK);
	
}
@PostMapping("/addEvresultat")
public ResponseEntity<EvresultatDto> addEvresultat(@RequestBody EvresultatDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Evresultat ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateEvresultat/{id}")
public ResponseEntity<EvresultatDto> updateEvresultat(@PathVariable int id,@RequestBody EvresultatDto dto) {
	if(objetRepo.existsById(id)) {
		Evresultat ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteEvresultat/{id}")
public ResponseEntity<EvresultatDto> deleteEvresultat(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Evresultat ab=objetRepo.deleteById(id);
		EvresultatDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<EvresultatDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<EvresultatDto>(HttpStatus.NOT_FOUND);
}
@PostMapping("/uploadResult")
public void handleFileUpload(@RequestPart(value="files[]", required = true) final MultipartFile[] files) {
	System.out.println("Nom original: " + files[0].getOriginalFilename() + "!");
	System.out.println("Type de contenu: " + files[0].getContentType());
	System.out.println("Nom: " + files[0].getName());
	System.out.println("Taille: " + files[0].getSize());
	String content;
	try {
		content = new String(files[0].getBytes(), StandardCharsets.UTF_8);
		System.out.println("Contenu" + content);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
@PostMapping("/addResults/{eventid}")
public void addResults(@PathVariable int eventid,@RequestPart(value="files[]", required = true) final MultipartFile[] files) {
	System.out.println("Nom original: " + files[0].getOriginalFilename() + "!");
	System.out.println("Type de contenu: " + files[0].getContentType());
	System.out.println("Nom: " + files[0].getName());
	System.out.println("Taille: " + files[0].getSize());
	List<Resultat>resultats=null;
	//String content;
	try {
		XLSXReader reader=new XLSXReader();
		//content = new String(files[0].getBytes(), StandardCharsets.UTF_8);
		resultats=reader.readFile(files[0].getBytes());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(resultats!=null) {
		List<Evresultat>evresulats=new ArrayList<>();
		Page<Evresultat> c2 =objetRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
		int max=c2.getContent().get(0).getId()+1;
		for(Resultat r:resultats) {
			Evresultat evR=new Evresultat();
			Champion c=cRepo.findByNom(r.getNom());
			if(c!=null) {
				evR.setChampion2(c);
				evR.setChampionID(c.getId());
			}
			
			evR.setClub(r.getClub());
			// ca depend evR.setEquipeID(eventid)
			evR.setId(max);
			evR.setRang(r.getRang());
			evR.setPoidID(r.getPoids());
			evR.setEvenementID(eventid);
			evresulats.add(evR);
			max+=1;
		}
		objetRepo.saveAll(evresulats);
		System.out.println("Resultats ajoutés avec succes !");
	}
	

}


}
