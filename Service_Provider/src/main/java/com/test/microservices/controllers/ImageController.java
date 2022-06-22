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

import com.test.microservices.dto.ImageDto;
import com.test.microservices.mappers.ImageDtoToImage;
import com.test.microservices.pojos.Image;
import com.test.microservices.repositories.ImageRepository;

@RestController
public class ImageController {
	ImageDtoToImage mapper;
	ImageRepository objetRepo;
	public ImageController(ImageRepository repo,ImageDtoToImage m) {
		this.objetRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getImageByIdMongo/{id}")
public ResponseEntity<ImageDto> getImage( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		Image ab=objetRepo.findByIdMongo( id);
		ImageDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ImageDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ImageDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getImageById/{id}")
public ResponseEntity<ImageDto> getImage( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Image ab=objetRepo.findById( id);
		ImageDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ImageDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ImageDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllImages")
public ResponseEntity<List<ImageDto>> getImage( ) {
	System.out.println("images par id ascendant");
	List<Image> lab=objetRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<ImageDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ImageDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllImageByChampionId/{id}")
public ResponseEntity<List<ImageDto>> getAllImageByChampionId( @PathVariable int id ) {
	System.out.println("images par id ascendant");
	List<Image> lab=objetRepo.getAllImageByChampion_id(id,Sort.by(Sort.Direction.ASC, "ID"));
	List<ImageDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ImageDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllImageByEvenementID/{id}")
public ResponseEntity<List<ImageDto>> getAllImageByEvenementID( @PathVariable int id ) {
	System.out.println("images par id ascendant");
	List<Image> lab=objetRepo.getAllImageByEvenement_id(id,Sort.by(Sort.Direction.ASC, "ID"));
	List<ImageDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ImageDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllImagesDesc")
public ResponseEntity<List<ImageDto>> getImageDesc( ) {
	System.out.println("images par date descendante");
	List<Image> lab=objetRepo.findAll(Sort.by(Sort.Direction.DESC, "ID"));
	List<ImageDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ImageDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addImage")
public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto dto) {
	if(!objetRepo.existsById(dto.getId())) {
		Image ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<ImageDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<ImageDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateImage/{id}")
public ResponseEntity<ImageDto> updateImage(@PathVariable int id,@RequestBody ImageDto dto) {
	if(objetRepo.existsById(id)) {
		Image ab=mapper.dtoToObject(dto);
		objetRepo.save(ab);
		return new ResponseEntity<ImageDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ImageDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteImage/{id}")
public ResponseEntity<ImageDto> deleteImage(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		Image ab=objetRepo.deleteById(id);
		ImageDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ImageDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ImageDto>(HttpStatus.NOT_FOUND);
}

}