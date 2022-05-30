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
import com.test.microservices.pojos.Video;
import com.test.microservices.repositories.VideoRepository;

@RestController
public class VideosController {
	VideoRepository videoRepo;
	VideoDtoToVideo mapper;
	public VideosController(VideoRepository repo,VideoDtoToVideo m) {
		this.videoRepo=repo;
		this.mapper=m;
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
	List<Video> lab=videoRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List <VideoDto>ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<VideoDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getSimilarsVideos/{cid}/{tid}/{t2id}/{eid}")
public ResponseEntity<List<VideoDto>> getSimilarsVideo(@PathVariable int cid,@PathVariable int tid,@PathVariable int t2id,@PathVariable int eid ) {
	if(cid==0) {
		cid=-1;
	}
	if(tid==0) {
		tid=-1;
	}
	if(t2id==0) {
		t2id=-1;
	}
	if(eid==0) {
		eid=-1;
	}
	Page<Video> lab=videoRepo.findSimilars(cid, tid, t2id, eid, PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "ID")));
	List <VideoDto>ldto=mapper.objectsToDtos(lab.getContent());
	return new ResponseEntity<List<VideoDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getByTitreOrCategorie/{mot_cle}/{categorie}")
public ResponseEntity<List<VideoDto>> getByTitreOrCategorie(@PathVariable String mot_cle,@PathVariable String categorie ) {
	if(!mot_cle.equals("any") && !categorie.equals("any")) {
		List<Video> lab=videoRepo.findByTitreAndCategorie(mot_cle, categorie, Sort.by(Sort.Direction.DESC, "ID"));
		List <VideoDto>ldto=mapper.objectsToDtos(lab);
		return new ResponseEntity<List<VideoDto>>(ldto,HttpStatus.OK);
	}
	List<Video> lab=videoRepo.findByTitreOrCategorie(mot_cle, categorie, Sort.by(Sort.Direction.DESC, "ID"));
	List <VideoDto>ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<VideoDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addVideo")
public ResponseEntity<VideoDto> addVideo(@RequestBody VideoDto ab) {
	if(!videoRepo.existsById(ab.getId())) {
		Video v=mapper.dtoToObject(ab);
		videoRepo.save(v);
		System.out.println("videoDto : "+ab);
		System.out.println("video : "+v);
		return new ResponseEntity<VideoDto>(ab,HttpStatus.CREATED);
	}
	return new ResponseEntity<VideoDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateVideo/{id}")
public ResponseEntity<VideoDto> updateVideo(@PathVariable int id,@RequestBody VideoDto ab) {
	if(videoRepo.existsById(id)) {
		Video objet=mapper.dtoToObject(ab);
		videoRepo.save(objet);
		System.out.println("videoDto : "+ab);
		System.out.println("video : "+objet);
		return new ResponseEntity<VideoDto>(ab,HttpStatus.OK);
	}
	return new ResponseEntity<VideoDto>(HttpStatus.NOT_FOUND);
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
