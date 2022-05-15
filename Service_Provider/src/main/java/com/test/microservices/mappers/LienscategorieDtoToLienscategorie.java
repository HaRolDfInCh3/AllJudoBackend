package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.LienscategorieDto;
import com.test.microservices.pojos.Lienscategorie;
@Service
public class LienscategorieDtoToLienscategorie implements DtoToObject<LienscategorieDto,Lienscategorie> {
	DozerBeanMapper modelMapper;
	@Override
	public Lienscategorie dtoToObject(LienscategorieDto lienscategorieDto) {
		this.modelMapper= new DozerBeanMapper();
		Lienscategorie lienscategorie=modelMapper.map(lienscategorieDto, Lienscategorie.class);
		return lienscategorie;
	}

	@Override
	public LienscategorieDto objectToDto(Lienscategorie lienscategorie) {
		this.modelMapper= new DozerBeanMapper();
		LienscategorieDto lienscategorieDto=modelMapper.map(lienscategorie, LienscategorieDto.class);
		return lienscategorieDto;
	}


	@Override
	public List<LienscategorieDto> objectsToDtos(List<Lienscategorie> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<LienscategorieDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(lienscategorie -> dtoList.add(modelMapper.map(lienscategorie, LienscategorieDto.class)));
		return dtoList;
	}



	

}
