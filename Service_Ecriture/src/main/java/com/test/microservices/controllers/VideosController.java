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

import com.test.microservices.dto.VideoDto;
import com.test.microservices.mappers.VideoDtoToVideo;
import com.test.microservices.pojos.Champion;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.pojos.Technique;
import com.test.microservices.pojos.Video;
import com.test.microservices.repositories.ChampionRepository;
import com.test.microservices.repositories.EvenementRepository;
import com.test.microservices.repositories.TechniqueRepository;
import com.test.microservices.repositories.VideoRepository;

@RestController
public class VideosController {
	VideoRepository videoRepo;
	VideoDtoToVideo mapper;
	EvenementRepository eRepo;
	TechniqueRepository tRepo;
	ChampionRepository cRepo;
	public VideosController(ChampionRepository c,TechniqueRepository t,EvenementRepository e,VideoRepository repo,VideoDtoToVideo m) {
		this.videoRepo=repo;
		this.mapper=m;
		this.cRepo=c;
		this.eRepo=e;
		this.tRepo=t;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getVideoByIdMongo/{id}")
public ResponseEntity<VideoDto> getVideo( @PathVariable String id) {
	if(videoRepo.existsByIdMongo(id)) {
		Video ab=videoRepo.findByIdMongo( id);
		VideoDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<VideoDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<VideoDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getVideoById/{id}")
public ResponseEntity<VideoDto> getVideo( @PathVariable int id) {
	if(videoRepo.existsById(id)) {
		Video ab=videoRepo.findById( id);
		VideoDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<VideoDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<VideoDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllVideos")
public ResponseEntity<List<VideoDto>> getVideo( ) {
	List<Video> lab=videoRepo.findAll();
	List <VideoDto>ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<VideoDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addVideo")
public ResponseEntity<VideoDto> addVideo(@RequestBody VideoDto ab) {
	Page<Video> c2 =videoRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
	Video v=mapper.dtoToObject(ab);
		int cid=ab.getChampion_id();
		int t1id=ab.getTechnique_id();
		int t2id=ab.getTechnique2_id();
		int eid=ab.getEvenement_id();
		if(cRepo.existsById(cid)) {
			Champion c=cRepo.findById(cid);
			v.setChampion(c);
		}if(tRepo.existsById(t2id)) {
			Technique t2=tRepo.findById(t2id);
			v.setTechnique2(t2);
		}if(tRepo.existsById(t1id)) {
			Technique t1=tRepo.findById(t1id);
			v.setTechnique(t1);
		}if(eRepo.existsById(eid)) {
			Evenement e=eRepo.findById(eid);
			v.setEvenement(e);
		}
		
		
		
		v.setId(max+1);
		ab.setId(max+1);
		videoRepo.save(v);
		return new ResponseEntity<VideoDto>(ab,HttpStatus.CREATED);
	
}
@PutMapping("/updateVideo/{id}")
public ResponseEntity<VideoDto> updateVideo(@PathVariable int id,@RequestBody VideoDto ab) {
	String idMongo=videoRepo.findById(id).getIdMongo();
		Video objet=mapper.dtoToObject(ab);
		int cid=ab.getChampion_id();
		int t1id=ab.getTechnique_id();
		int t2id=ab.getTechnique2_id();
		int eid=ab.getEvenement_id();
		if(cRepo.existsById(cid)) {
			Champion c=cRepo.findById(cid);
			objet.setChampion(c);
		}if(tRepo.existsById(t2id)) {
			Technique t2=tRepo.findById(t2id);
			objet.setTechnique2(t2);
		}if(tRepo.existsById(t1id)) {
			Technique t1=tRepo.findById(t1id);
			objet.setTechnique(t1);
		}if(eRepo.existsById(eid)) {
			Evenement e=eRepo.findById(eid);
			objet.setEvenement(e);
		}
		videoRepo.deleteById(idMongo); 
		objet.setIdMongo(idMongo);
		videoRepo.save(objet);
		return new ResponseEntity<VideoDto>(ab,HttpStatus.OK);
	
}
@DeleteMapping("/deleteVideo/{id}")
public ResponseEntity<VideoDto> deleteVideo(@PathVariable int id) {
	if(videoRepo.existsById(id)) {
		Video ab=videoRepo.deleteById(id);
		VideoDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<VideoDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<VideoDto>(HttpStatus.NOT_FOUND);
}


}
