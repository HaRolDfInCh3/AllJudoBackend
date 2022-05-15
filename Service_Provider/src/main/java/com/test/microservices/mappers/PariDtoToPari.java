package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.PariDto;
import com.test.microservices.pojos.Pari;
@Service
public class PariDtoToPari implements DtoToObject<PariDto,Pari> {
	DozerBeanMapper modelMapper;
	@Override
	public Pari dtoToObject(PariDto pariDto) {
		this.modelMapper= new DozerBeanMapper();
		Pari pari=modelMapper.map(pariDto, Pari.class);
		return pari;
	}

	@Override
	public PariDto objectToDto(Pari pari) {
		this.modelMapper= new DozerBeanMapper();
		PariDto pariDto=modelMapper.map(pari, PariDto.class);
		return pariDto;
	}


	@Override
	public List<PariDto> objectsToDtos(List<Pari> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<PariDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(pari -> dtoList.add(modelMapper.map(pari, PariDto.class)));
		return dtoList;
	}



	

}
