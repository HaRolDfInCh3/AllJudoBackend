package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Technique_specialisteDto;
import com.test.microservices.pojos.Technique_specialiste;
@Service
public class Technique_specialisteDtoToTechnique_specialiste implements DtoToObject<Technique_specialisteDto,Technique_specialiste> {
	DozerBeanMapper modelMapper;
	@Override
	public Technique_specialiste dtoToObject(Technique_specialisteDto technique_specialisteDto) {
		this.modelMapper= new DozerBeanMapper();
		Technique_specialiste technique_specialiste=modelMapper.map(technique_specialisteDto, Technique_specialiste.class);
		return technique_specialiste;
	}

	@Override
	public Technique_specialisteDto objectToDto(Technique_specialiste technique_specialiste) {
		this.modelMapper= new DozerBeanMapper();
		Technique_specialisteDto technique_specialisteDto=modelMapper.map(technique_specialiste, Technique_specialisteDto.class);
		return technique_specialisteDto;
	}


	@Override
	public List<Technique_specialisteDto> objectsToDtos(List<Technique_specialiste> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Technique_specialisteDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(technique_specialiste -> dtoList.add(modelMapper.map(technique_specialiste, Technique_specialisteDto.class)));
		return dtoList;
	}



	

}
