package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.RoleDto;
import com.example.demo.mappers.RoleDtoToRole;
import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
@RestController
public class RoleController {
	RoleDtoToRole mapper;
	RoleRepository roleRepo;
	public RoleController(RoleRepository repo,RoleDtoToRole m) {
		this.roleRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getRoleByIdMongo/{id}")
public ResponseEntity<RoleDto> getRole( @PathVariable String id) {
	if(roleRepo.existsByIdMongo(id)) {
		Role ab=roleRepo.findByIdMongo( id);
		RoleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<RoleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RoleDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getRoleById/{id}")
public ResponseEntity<RoleDto> getRole( @PathVariable int id) {
	if(roleRepo.existsById(id)) {
		Role ab=roleRepo.findById( id);
		RoleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<RoleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RoleDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllRoles")
public ResponseEntity<List<RoleDto>> getRole( ) {
	List<Role> lab=roleRepo.findAll();
	List<RoleDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<RoleDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addRole")
public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto dto) {
	if(!roleRepo.existsById(dto.getId())) {
		Role ab=mapper.dtoToObject(dto);
		roleRepo.save(ab);
		return new ResponseEntity<RoleDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<RoleDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateRole/{id}")
public ResponseEntity<RoleDto> updateRole(@PathVariable int id,@RequestBody RoleDto dto) {
	if(roleRepo.existsById(id)) {
		Role ab=mapper.dtoToObject(dto);
		roleRepo.save(ab);
		return new ResponseEntity<RoleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RoleDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteRole/{id}")
public ResponseEntity<RoleDto> deleteRole(@PathVariable int id) {
	if(roleRepo.existsById(id)) {
		Role ab=roleRepo.deleteById(id);
		RoleDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<RoleDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<RoleDto>(HttpStatus.NOT_FOUND);
}



}
