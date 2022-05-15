package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.OptionDto;
import com.test.microservices.pojos.Option;
@Service
public class OptionDtoToOption implements DtoToObject<OptionDto,Option> {
	DozerBeanMapper modelMapper;
	@Override
	public Option dtoToObject(OptionDto optionDto) {
		this.modelMapper= new DozerBeanMapper();
		Option option=modelMapper.map(optionDto, Option.class);
		return option;
	}

	@Override
	public OptionDto objectToDto(Option option) {
		this.modelMapper= new DozerBeanMapper();
		OptionDto optionDto=modelMapper.map(option, OptionDto.class);
		return optionDto;
	}


	@Override
	public List<OptionDto> objectsToDtos(List<Option> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<OptionDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(option -> dtoList.add(modelMapper.map(option, OptionDto.class)));
		return dtoList;
	}



	

}
