package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.AnnonceDto;
import com.test.microservices.pojos.Annonce;
@Service
public class AnnonceDtoToAnnonce implements DtoToObject<AnnonceDto,Annonce> {
	DozerBeanMapper modelMapper;
	@Override
	public Annonce dtoToObject(AnnonceDto annonceDto) {
		this.modelMapper= new DozerBeanMapper();
		Annonce annonce=modelMapper.map(annonceDto, Annonce.class);
		return annonce;
	}

	@Override
	public AnnonceDto objectToDto(Annonce annonce) {
		this.modelMapper= new DozerBeanMapper();
		AnnonceDto annonceDto=modelMapper.map(annonce, AnnonceDto.class);
		return annonceDto;
	}


	@Override
	public List<AnnonceDto> objectsToDtos(List<Annonce> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<AnnonceDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(annonce -> dtoList.add(modelMapper.map(annonce, AnnonceDto.class)));
		return dtoList;
	}



	

}
