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

import com.test.microservices.dto.CommentaireDto;
import com.test.microservices.mappers.CommentaireDtoToCommentaire;
import com.test.microservices.pojos.Champion;
import com.test.microservices.pojos.Commentaire;
import com.test.microservices.pojos.News;
import com.test.microservices.pojos.User;
import com.test.microservices.pojos.Video;
import com.test.microservices.repositories.ChampionRepository;
import com.test.microservices.repositories.CommentairesRepository;
import com.test.microservices.repositories.NewsRepository;
import com.test.microservices.repositories.UserRepository;
import com.test.microservices.repositories.VideoRepository;
@RestController
public class CommentaireController {
	CommentaireDtoToCommentaire mapper;
	CommentairesRepository commentaireRepo;
	VideoRepository vRepo;
	ChampionRepository cRepo;
	NewsRepository nRepo;
	UserRepository uRepo;
	public CommentaireController(UserRepository u,NewsRepository n,VideoRepository v,ChampionRepository c,CommentairesRepository repo,CommentaireDtoToCommentaire m) {
		this.commentaireRepo=repo;
		this.vRepo=v;
		this.uRepo=u;
		this.cRepo=c;
		this.nRepo=n;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getCommentaireByIdMongo/{id}")
public ResponseEntity<CommentaireDto> getCommentaire( @PathVariable String id) {
	if(commentaireRepo.existsByIdMongo(id)) {
		Commentaire ab=commentaireRepo.findByIdMongo( id);
		CommentaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<CommentaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CommentaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getCommentaireById/{id}")
public ResponseEntity<CommentaireDto> getCommentaire( @PathVariable int id) {
	if(commentaireRepo.existsById(id)) {
		Commentaire ab=commentaireRepo.findById( id);
		CommentaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<CommentaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CommentaireDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllCommentaires")
public ResponseEntity<List<CommentaireDto>> getCommentaire( ) {
	List<Commentaire> lab=commentaireRepo.findAll();
	List<CommentaireDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<CommentaireDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addCommentaire")
public ResponseEntity<CommentaireDto> addCommentaire(@RequestBody CommentaireDto dto) {
	Page<Commentaire> c2 =commentaireRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		Commentaire ab=mapper.dtoToObject(dto);
		int uid=ab.getUser_id();
		int nid=ab.getNews_id();
		int vid=ab.getVideo_id();
		int cid=ab.getChampion_id();
		if(uRepo.existsById(uid)) {
			System.out.println("Userid");
			User u=uRepo.findById(uid);
			ab.setUser2(u);
			
		}if(vRepo.existsById(vid)) {
			Video v=vRepo.findById(vid);
			ab.setVideo2(v);
		}if(cRepo.existsById(cid)) {
			Champion c=cRepo.findById(cid);
			ab.setChampion2(c);
		}if(nRepo.existsById(uid)) {
			News n=nRepo.findById(nid);
			ab.setNews2(n);
		}
		ab.setId(max+1);
		commentaireRepo.save(ab);
		dto=mapper.objectToDto(ab);
		return new ResponseEntity<CommentaireDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateCommentaire/{id}")
public ResponseEntity<CommentaireDto> updateCommentaire(@PathVariable int id,@RequestBody CommentaireDto dto) {
	if(commentaireRepo.existsById(id)) {
		Commentaire ab=mapper.dtoToObject(dto);
		commentaireRepo.save(ab);
		return new ResponseEntity<CommentaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CommentaireDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteCommentaire/{id}")
public ResponseEntity<CommentaireDto> deleteCommentaire(@PathVariable int id) {
	if(commentaireRepo.existsById(id)) {
		Commentaire ab=commentaireRepo.deleteById(id);
		CommentaireDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<CommentaireDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<CommentaireDto>(HttpStatus.NOT_FOUND);
}



}
