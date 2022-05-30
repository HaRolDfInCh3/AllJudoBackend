package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.UserDtoToUser;
import com.example.demo.entities.Pays;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.enums.Designation_role;
import com.example.demo.repositories.PaysRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.Mail;
import com.example.demo.services.MailService;


@RestController
public class UserController {
	@Autowired
	MailService mailService;
	RoleRepository roleRepo;
	UserRepository userRepo;
	UserDtoToUser mapper;
	PaysRepository paysRepo;
	private PasswordEncoder pe;
	public UserController(RoleRepository r,PasswordEncoder pen,PaysRepository p,UserRepository repo,UserDtoToUser m) {
		this.userRepo=repo;
		this.pe=pen;
		this.paysRepo=p;
		this.roleRepo=r;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getUserByIdMongo/{id}")
public ResponseEntity<UserDto> getUser( @PathVariable String id) {
	if(userRepo.existsByIdMongo(id)) {
		User ab=userRepo.findByIdMongo( id);
		UserDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getUserById/{id}")
public ResponseEntity<UserDto> getUser( @PathVariable int id) {
	if(userRepo.existsById(id)) {
		User ab=userRepo.findById( id);
		UserDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getUserByUsername/{username}")
public ResponseEntity<UserDto> getUserByUsername( @PathVariable String username) {
	if(userRepo.existsByUsername(username)) {
		List<User> ab=userRepo.findByUsername(username);
		UserDto dto=mapper.objectToDto(ab.get(0));
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllUsers")
public ResponseEntity<List<UserDto>> getUser( ) {
	List<User> lab=userRepo.findAll();
	List<UserDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<UserDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addUser")
public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
	Page<User> c2 =userRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);
		User ab=mapper.dtoToObject(dto);
		if(paysRepo.existsByAbreviation(ab.getPays())) {
			Pays p=paysRepo.findByAbreviation(ab.getPays());
			ab.setPays2(p);
		}
		 String motdepasse=ab.getPassword();
		 ab.setPassword(pe.encode(motdepasse));
		 Role r=roleRepo.findByDesignation(Designation_role.User.name());
		List<Role>roles=new ArrayList<>();
		roles.add(r);
		ab.setRoles(roles);
		
		ab.setId(max+1);
		dto.setId(max+1);
		userRepo.save(ab);
		String email=ab.getEmail();
		Mail mail = new Mail();
        mail.setMailFrom("ayrtongonsallo444@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Bienvenue sur AllJudo");
        mail.setMailContent("Cher(e) "+ab.getNom()+" "+ab.getPrenom()+",\nVous venez en effet de creer votre compte");
        mailService.sendEmail(mail);
		return new ResponseEntity<UserDto>(dto,HttpStatus.CREATED);
	
}
@PutMapping("/updateUser/{id}")
public ResponseEntity<UserDto> updateUser(@PathVariable int id,@RequestBody UserDto dto) {
	String idMongo=userRepo.findById(id).getIdMongo();
		User ab=mapper.dtoToObject(dto);
		if(paysRepo.existsByAbreviation(ab.getPays())) {
			Pays p=paysRepo.findByAbreviation(ab.getPays());
			ab.setPays2(p);
		}
		userRepo.deleteById(idMongo);
		ab.setIdMongo(idMongo);
		
		userRepo.save(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	
}
@DeleteMapping("/deleteUser/{id}")
public ResponseEntity<UserDto> deleteUser(@PathVariable int id) {
	if(userRepo.existsById(id)) {
		User ab=userRepo.deleteById(id);
		UserDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<UserDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
}


}

