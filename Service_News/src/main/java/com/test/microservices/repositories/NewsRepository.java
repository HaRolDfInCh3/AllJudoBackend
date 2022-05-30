package com.test.microservices.repositories;









import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import com.test.microservices.pojos.News;

public interface NewsRepository extends MongoRepository<News, String> {
	public News findById(int id2);
	public Boolean existsById(int id2);
	public News findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public News deleteById(int id2);
	@Query("{aLaDeux:true}")
	public Page<News> findAllNewsALaDeux(Pageable p);
	@Query("{aLaUne:true}")
	public Page<News> findAllNewsALaUne(Pageable p);
	@Query(" {chapo : { $regex: '.*?0.*' },'Newscategorie2.Intitule':{ $regex: '.*?1.*' }}")
	public List<News> findNewsByCategorieAndChapo(String chapo,String categorie);
	@Query("{'Newscategorie2.Intitule':'Breve'}")
	public Page<News> findBrevesNews(Pageable p);
	@Query("{'Newscategorie2.Intitule':'?0','Type':'?1',$nor:[{'ID':?2}]}")
	public Page<News> findNewsByCategorieAndType(String categorie,String type,int id,Pageable p);
	
}
