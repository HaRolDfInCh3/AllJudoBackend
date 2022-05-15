package com.example.ApiDoublons.web;

import java.util.ArrayList;
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

import com.example.ApiDoublons.dtos.ChampionDto;
import com.example.ApiDoublons.mappers.ChampionDtoToChampion;
import com.example.ApiDoublons.entities.Champion;
import com.example.ApiDoublons.entities.ListeDeDoublons;
import com.example.ApiDoublons.repositories.ChampionRepository;
import com.example.ApiDoublons.services.Search;
import com.example.ApiDoublons.services.Singleton;

@RestController
public class ChampionController {
	ChampionDtoToChampion mapper;
	ChampionRepository championRepo;
	public ChampionController(ChampionRepository repo,ChampionDtoToChampion m) {
		this.championRepo=repo;
		this.mapper=m;
		// TODO Auto-generated constructor stub
	}
@GetMapping("/getChampionByIdMongo/{id}")
public ResponseEntity<ChampionDto> getChampion( @PathVariable String id) {
	if(championRepo.existsByIdMongo(id)) {
		Champion ab=championRepo.findByIdMongo( id);
		ChampionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getChampionById/{id}")
public ResponseEntity<ChampionDto> getChampion( @PathVariable int id) {
	if(championRepo.existsById(id)) {
		Champion ab=championRepo.findById( id);
		ChampionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping("/getAllChampions")
public ResponseEntity<List<ChampionDto>> getChampion( ) {
	List<Champion> lab=championRepo.findAll();
	List<ChampionDto> ldto=mapper.objectsToDtos(lab);
	return new ResponseEntity<List<ChampionDto>>(ldto,HttpStatus.OK);
}
@PostMapping("/addChampion")
public ResponseEntity<ChampionDto> addChampion(@RequestBody ChampionDto dto) {
	if(!championRepo.existsById(dto.getId())) {
		Champion ab=mapper.dtoToObject(dto);
		championRepo.save(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.CREATED);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.CONFLICT);
}
@PutMapping("/updateChampion/{id}")
public ResponseEntity<ChampionDto> updateChampion(@PathVariable int id,@RequestBody ChampionDto dto) {
	if(championRepo.existsById(id)) {
		Champion ab=mapper.dtoToObject(dto);
		championRepo.save(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@DeleteMapping("/deleteChampion/{id}")
public ResponseEntity<ChampionDto> deleteChampion(@PathVariable int id) {
	if(championRepo.existsById(id)) {
		Champion ab=championRepo.deleteById(id);
		ChampionDto dto=mapper.objectToDto(ab);
		return new ResponseEntity<ChampionDto>(dto,HttpStatus.OK);
	}
	return new ResponseEntity<ChampionDto>(HttpStatus.NOT_FOUND);
}
@GetMapping(path="/get_Similars/{id}")
public List<ChampionDto> getSimilars(@PathVariable int id){
	Singleton.getInstance().setParlots(false);
	Singleton.getInstance().clear();
	
	if(championRepo.existsById(id)){
		Champion c=championRepo.findById(id);
		System.out.println(c.getNom());
		Singleton.getInstance().setChampion(c); 
		Search.getSimilar(id);
		int n=Search.nbrdethreads;
		while(true){
			if(Singleton.getInstance().getFinis()==n){
				break;
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	List<ChampionDto>dtos=mapper.objectsToDtos(Singleton.getInstance().getResultats());
	return dtos;
	
}


@GetMapping(path="/get_Similars/{deb}/{fin}",produces = "application/json;charset=UTF-8")
public ResponseEntity<Object> getALLSimilars(@PathVariable int deb,@PathVariable int fin){
	List <ListeDeDoublons> ld=new ArrayList<>();
	Singleton.getInstance().setParlots(true);
	for(int i=deb;i<fin;i++){
		if(championRepo.existsById(i)){
			Champion c=championRepo.findById(i);
			int curr=i;
			Thread t=new Thread(){
				public void run() {
					Singleton.getInstance().getResultats2().add(new ArrayList<Champion>());
					Singleton.getInstance().getPos().add(curr);
					Singleton.getInstance().clear();
					
					System.out.println("Debut Thread "+c.getId()+" "+c.getNom());
					Singleton.getInstance().setChampion(c); 
					Search.getSimilar(curr);
					int n=Search.nbrdethreads;
					while(true){
						if(Singleton.getInstance().getFinis()==n){
							break;
						}
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ListeDeDoublons ldd=new ListeDeDoublons();
					ldd.setChampion(c);
					ldd.setDoublons(Singleton.getInstance().getResultats2().get(Singleton.getInstance().getPos().indexOf(curr)));
					if(ldd.getDoublons().size()>0){
						ld.add(ldd);
					}
					System.out.println("fin thread ");
				};
			};
			t.start();
			try {
				t.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	}
		
	System.out.println("nbr: "+ld.size());
	//System.out.println(ld);
	return  new ResponseEntity<Object>(ld, HttpStatus.OK);
	
}

}
