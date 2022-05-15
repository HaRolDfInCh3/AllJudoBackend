package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.NationalityDto;
import com.test.microservices.pojos.Nationality;
@Service
public class NationalityDtoToNationality implements DtoToObject<NationalityDto,Nationality> {
	DozerBeanMapper modelMapper;
	@Override
	public Nationality dtoToObject(NationalityDto nationalityDto) {
		this.modelMapper= new DozerBeanMapper();
		Nationality nationality=modelMapper.map(nationalityDto, Nationality.class);
		return nationality;
	}

	@Override
	public NationalityDto objectToDto(Nationality nationality) {
		this.modelMapper= new DozerBeanMapper();
		NationalityDto nationalityDto=modelMapper.map(nationality, NationalityDto.class);
		return nationalityDto;
	}


	@Override
	public List<NationalityDto> objectsToDtos(List<Nationality> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<NationalityDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(nationality -> dtoList.add(modelMapper.map(nationality, NationalityDto.class)));
		return dtoList;
	}



	

}
