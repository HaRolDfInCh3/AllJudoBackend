package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.LivreDto;
import com.test.microservices.pojos.Livre;
@Service
public class LivreDtoToLivre implements DtoToObject<LivreDto,Livre> {
	DozerBeanMapper modelMapper;
	@Override
	public Livre dtoToObject(LivreDto livreDto) {
		this.modelMapper= new DozerBeanMapper();
		Livre livre=modelMapper.map(livreDto, Livre.class);
		return livre;
	}

	@Override
	public LivreDto objectToDto(Livre livre) {
		this.modelMapper= new DozerBeanMapper();
		LivreDto livreDto=modelMapper.map(livre, LivreDto.class);
		return livreDto;
	}


	@Override
	public List<LivreDto> objectsToDtos(List<Livre> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<LivreDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(livre -> dtoList.add(modelMapper.map(livre, LivreDto.class)));
		return dtoList;
	}



	

}
