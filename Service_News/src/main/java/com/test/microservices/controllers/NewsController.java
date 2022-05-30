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

import com.test.microservices.dto.NewsDto;
import com.test.microservices.mappers.NewsDtoToNews;
import com.test.microservices.pojos.Admin;
import com.test.microservices.pojos.Evenement;
import com.test.microservices.pojos.News;
import com.test.microservices.pojos.Newscategorie;
import com.test.microservices.repositories.AdminRepository;
import com.test.microservices.repositories.EvenementRepository;
import com.test.microservices.repositories.NewsRepository;
import com.test.microservices.repositories.NewscategorieRepository;
@RestController
public class NewsController {
	NewsDtoToNews mapper;
	NewsRepository objetRepo;
	NewscategorieRepository ncRepo;
	EvenementRepository eveRepo;
	AdminRepository adRepo;
	public NewsController(AdminRepository ad,NewsRepository repo,NewscategorieRepository nc,EvenementRepository ev,NewsDtoToNews m) {
		this.objetRepo=repo;
		this.mapper=m;
		this.adRepo=ad;
		this.eveRepo=ev;
		this.ncRepo=nc;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getNewsByCategorieAndType/{cat}/{type}/{curid}")
public ResponseEntity<List<NewsDto>> getNewsByCategorieAndType( @PathVariable String cat,@PathVariable String type,@PathVariable int curid) {
	
		Page<News> ab=objetRepo.findNewsByCategorieAndType(cat, type,curid, PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "date")));
		List<NewsDto> dto=mapper.objectsToDtos(ab.getContent());
		return new ResponseEntity<List<NewsDto>>(dto,HttpStatus.OK);
	
}
@GetMapping("/getNewsByIdMongo/{id}")
public ResponseEntity<NewsDto> getNews( @PathVariable String id) {
	if(objetRepo.existsByIdMongo(id)) {
		News ab=objetRepo.findByIdMongo( id);
		NewsDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<NewsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NewsDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getNewsById/{id}")
public ResponseEntity<NewsDto> getNews( @PathVariable int id) {
	if(objetRepo.existsById(id)) {
		News ab=objetRepo.findById( id);
		NewsDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<NewsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NewsDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllNewss")
public ResponseEntity<List<NewsDto>> getNews( ) {
	List<News> lab=objetRepo.findAll(Sort.by(Sort.Direction.ASC, "ID"));
	List<NewsDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getAllNewsByDateDesc")
public ResponseEntity<List<NewsDto>> getAllNewsByDateDesc( ) {
	List<News> lab=objetRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
	List<NewsDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getLatestNews")
public ResponseEntity<List<NewsDto>> getLatestNews( ) {
	Page<News> c2 =objetRepo.findAll(PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "date")));

	List<NewsDto> ldto=mapper.objectsToDtos(c2.getContent());
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getBrevesNews")
public ResponseEntity<List<NewsDto>> getBrevesNews( ) {
	Page<News> c2 =objetRepo.findBrevesNews(PageRequest.of(0, 7, Sort.by(Sort.Direction.DESC, "date")));

	List<NewsDto> ldto=mapper.objectsToDtos(c2.getContent());
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getLatestNewsAladeux/{total}")
public ResponseEntity<List<NewsDto>> getLatestNewsAlaDeux(@PathVariable int total ) {
	Page<News> c2 =objetRepo.findAllNewsALaDeux(PageRequest.of(0, total, Sort.by(Sort.Direction.DESC, "date")));

	List<NewsDto> ldto=mapper.objectsToDtos(c2.getContent());
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getNewsByCategorieAndChapo/{categorie}/{chapo}")
public ResponseEntity<List<NewsDto>> getNewsByCategorieAndChapo(@PathVariable String categorie,@PathVariable String chapo ) {
	if(categorie.equalsIgnoreCase("any")) {
		categorie="";
	}if(chapo.equalsIgnoreCase("any")) {
		chapo="";
	}
	List<News> c2 =objetRepo.findNewsByCategorieAndChapo(chapo,categorie);

	List<NewsDto> ldto=mapper.objectsToDtos(c2);
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@GetMapping("/getLatestNewsAlaUne/{total}")
public ResponseEntity<List<NewsDto>> getLatestNewsAlaUne(@PathVariable int total ) {
	Page<News> c2 =objetRepo.findAllNewsALaUne(PageRequest.of(0, total, Sort.by(Sort.Direction.DESC, "date")));

	List<NewsDto> ldto=mapper.objectsToDtos(c2.getContent());
	return new ResponseEntity<List<NewsDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addNews")
public ResponseEntity<NewsDto> addNews(@RequestBody NewsDto dto) {
	System.out.println("recu: "+dto);
	Page<News> c2 =objetRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "ID")));
	int max=c2.getContent().get(0).getId();
	System.out.println("Id max: "+max);	
		//on suppose que les objets references existent deja
		News ab=mapper.dtoToObject(dto);
		int ncat=ab.getCategorieID();
		String adm=ab.getAdmin();
		int eve=ab.getEvenementID();
		if(eve!=0) {
			Evenement ev= eveRepo.findById(eve);
			System.out.println("Evenement trouvé");
			ab.setEvenement2(ev);
		}
		if(ncat!=0) {
			Newscategorie nc=ncRepo.findById(ncat);
			System.out.println("Categorie trouvé");
			ab.setNewscategorie2(nc);
		}
		if(adm!=null) {
			Admin admin=adRepo.findByLogin(adm);
			System.out.println("Admin trouvé");
			ab.setAdmin2(admin);
		}
		if(ab.photo!=null) {
			String url=ab.photo.replace("C:\\fakepath\\", "");
			ab.setPhoto(url);
		}
		ab.setId(max+1);
		System.out.println(ab);
		objetRepo.save(ab);
		return new ResponseEntity<NewsDto>(dto,HttpStatus.CREATED);
}
@PutMapping("/updateNews/{id}")
public ResponseEntity<NewsDto> updateNews(@PathVariable int id,@RequestBody NewsDto dto) {
	if(objetRepo.existsById(id)) {
		News ancienne=objetRepo.findById(id);
		String idMongo=ancienne.getIdMongo();
		News ab=mapper.dtoToObject(dto);
		ab.setIdMongo(idMongo);
		int ncat=ab.getCategorieID();
		String adm=ab.getAdmin();
		int eve=ab.getEvenementID();
		if(eve!=0) {
			Evenement ev= eveRepo.findById(eve);
			System.out.println("Evenement trouvé");
			ab.setEvenement2(ev);
		}
		if(ncat!=0) {
			Newscategorie nc=ncRepo.findById(ncat);
			System.out.println("Categorie trouvé");
			ab.setNewscategorie2(nc);
		}
		if(adm!=null) {
			Admin admin=adRepo.findByLogin(adm);
			System.out.println("Admin trouvé");
			ab.setAdmin2(admin);
		}
		System.out.println(ab);
		objetRepo.deleteById(idMongo);
		objetRepo.save(ab);
		return new ResponseEntity<NewsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NewsDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteNews/{id}")
public ResponseEntity<NewsDto> deleteNews(@PathVariable int id) {
	if(objetRepo.existsById(id)) {
		News ab=objetRepo.deleteById(id);
		NewsDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<NewsDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<NewsDto>(HttpStatus.NOT_FOUND);
}


}
