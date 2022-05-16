package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.TechniqueDto;
import com.test.microservices.pojos.Technique;
@Service
public class TechniqueDtoToTechnique implements DtoToObject<TechniqueDto,Technique> {
	DozerBeanMapper modelMapper;
	@Override
	public Technique dtoToObject(TechniqueDto techniqueDto) {
		this.modelMapper= new DozerBeanMapper();
		Technique technique=modelMapper.map(techniqueDto, Technique.class);
		return technique;
	}

	@Override
	public TechniqueDto objectToDto(Technique technique) {
		this.modelMapper= new DozerBeanMapper();
		TechniqueDto techniqueDto=modelMapper.map(technique, TechniqueDto.class);
		return techniqueDto;
	}


	@Override
	public List<TechniqueDto> objectsToDtos(List<Technique> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<TechniqueDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(technique -> dtoList.add(modelMapper.map(technique, TechniqueDto.class)));
		return dtoList;
	}



	

}
