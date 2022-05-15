package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Pari_compositionDto;
import com.test.microservices.pojos.Pari_composition;
@Service
public class Pari_compositionDtoToPari_composition implements DtoToObject<Pari_compositionDto,Pari_composition> {
	DozerBeanMapper modelMapper;
	@Override
	public Pari_composition dtoToObject(Pari_compositionDto pari_compositionDto) {
		this.modelMapper= new DozerBeanMapper();
		Pari_composition pari_composition=modelMapper.map(pari_compositionDto, Pari_composition.class);
		return pari_composition;
	}

	@Override
	public Pari_compositionDto objectToDto(Pari_composition pari_composition) {
		this.modelMapper= new DozerBeanMapper();
		Pari_compositionDto pari_compositionDto=modelMapper.map(pari_composition, Pari_compositionDto.class);
		return pari_compositionDto;
	}


	@Override
	public List<Pari_compositionDto> objectsToDtos(List<Pari_composition> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Pari_compositionDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(pari_composition -> dtoList.add(modelMapper.map(pari_composition, Pari_compositionDto.class)));
		return dtoList;
	}



	

}
