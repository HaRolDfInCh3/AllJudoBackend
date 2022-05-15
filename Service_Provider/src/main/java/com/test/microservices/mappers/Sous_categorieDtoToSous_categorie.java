package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Sous_categorieDto;
import com.test.microservices.pojos.Sous_categorie;
@Service
public class Sous_categorieDtoToSous_categorie implements DtoToObject<Sous_categorieDto,Sous_categorie> {
	DozerBeanMapper modelMapper;
	@Override
	public Sous_categorie dtoToObject(Sous_categorieDto sous_categorieDto) {
		this.modelMapper= new DozerBeanMapper();
		Sous_categorie sous_categorie=modelMapper.map(sous_categorieDto, Sous_categorie.class);
		return sous_categorie;
	}

	@Override
	public Sous_categorieDto objectToDto(Sous_categorie sous_categorie) {
		this.modelMapper= new DozerBeanMapper();
		Sous_categorieDto sous_categorieDto=modelMapper.map(sous_categorie, Sous_categorieDto.class);
		return sous_categorieDto;
	}


	@Override
	public List<Sous_categorieDto> objectsToDtos(List<Sous_categorie> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Sous_categorieDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(sous_categorie -> dtoList.add(modelMapper.map(sous_categorie, Sous_categorieDto.class)));
		return dtoList;
	}



	

}
