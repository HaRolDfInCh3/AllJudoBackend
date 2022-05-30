package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Pari_compositionElementDto;
import com.test.microservices.pojos.Pari_compositionElement;
@Service
public class Pari_compositionElementDtoToPari_compositionElement implements DtoToObject<Pari_compositionElementDto,Pari_compositionElement> {
	DozerBeanMapper modelMapper;
	@Override
	public Pari_compositionElement dtoToObject(Pari_compositionElementDto pari_compositionElementDto) {
		this.modelMapper= new DozerBeanMapper();
		Pari_compositionElement pari_compositionElement=modelMapper.map(pari_compositionElementDto, Pari_compositionElement.class);
		return pari_compositionElement;
	}

	@Override
	public Pari_compositionElementDto objectToDto(Pari_compositionElement pari_compositionElement) {
		this.modelMapper= new DozerBeanMapper();
		Pari_compositionElementDto pari_compositionElementDto=modelMapper.map(pari_compositionElement, Pari_compositionElementDto.class);
		return pari_compositionElementDto;
	}


	@Override
	public List<Pari_compositionElementDto> objectsToDtos(List<Pari_compositionElement> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Pari_compositionElementDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(pari_compositionElement -> dtoList.add(modelMapper.map(pari_compositionElement, Pari_compositionElementDto.class)));
		return dtoList;
	}



	

}
