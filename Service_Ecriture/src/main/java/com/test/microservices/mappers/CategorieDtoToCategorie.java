package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.CategorieDto;
import com.test.microservices.pojos.Categorie;
@Service
public class CategorieDtoToCategorie implements DtoToObject<CategorieDto,Categorie> {
	DozerBeanMapper modelMapper;
	@Override
	public Categorie dtoToObject(CategorieDto categorieDto) {
		this.modelMapper= new DozerBeanMapper();
		Categorie categorie=modelMapper.map(categorieDto, Categorie.class);
		return categorie;
	}

	@Override
	public CategorieDto objectToDto(Categorie categorie) {
		this.modelMapper= new DozerBeanMapper();
		CategorieDto categorieDto=modelMapper.map(categorie, CategorieDto.class);
		return categorieDto;
	}


	@Override
	public List<CategorieDto> objectsToDtos(List<Categorie> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<CategorieDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(categorie -> dtoList.add(modelMapper.map(categorie, CategorieDto.class)));
		return dtoList;
	}



	

}
