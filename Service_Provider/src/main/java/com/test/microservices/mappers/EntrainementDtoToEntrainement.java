package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.EntrainementDto;
import com.test.microservices.pojos.Entrainement;
@Service
public class EntrainementDtoToEntrainement implements DtoToObject<EntrainementDto,Entrainement> {
	DozerBeanMapper modelMapper;
	@Override
	public Entrainement dtoToObject(EntrainementDto entrainementDto) {
		this.modelMapper= new DozerBeanMapper();
		Entrainement entrainement=modelMapper.map(entrainementDto, Entrainement.class);
		return entrainement;
	}

	@Override
	public EntrainementDto objectToDto(Entrainement entrainement) {
		this.modelMapper= new DozerBeanMapper();
		EntrainementDto entrainementDto=modelMapper.map(entrainement, EntrainementDto.class);
		return entrainementDto;
	}


	@Override
	public List<EntrainementDto> objectsToDtos(List<Entrainement> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<EntrainementDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(entrainement -> dtoList.add(modelMapper.map(entrainement, EntrainementDto.class)));
		return dtoList;
	}



	

}
