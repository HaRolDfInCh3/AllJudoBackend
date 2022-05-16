package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Champion_populariteDto;
import com.test.microservices.pojos.Champion_popularite;
@Service
public class Champion_populariteDtoToChampion_popularite implements DtoToObject<Champion_populariteDto,Champion_popularite> {
	DozerBeanMapper modelMapper;
	@Override
	public Champion_popularite dtoToObject(Champion_populariteDto champion_populariteDto) {
		this.modelMapper= new DozerBeanMapper();
		Champion_popularite champion_popularite=modelMapper.map(champion_populariteDto, Champion_popularite.class);
		return champion_popularite;
	}

	@Override
	public Champion_populariteDto objectToDto(Champion_popularite champion_popularite) {
		this.modelMapper= new DozerBeanMapper();
		Champion_populariteDto champion_populariteDto=modelMapper.map(champion_popularite, Champion_populariteDto.class);
		return champion_populariteDto;
	}


	@Override
	public List<Champion_populariteDto> objectsToDtos(List<Champion_popularite> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Champion_populariteDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(champion_popularite -> dtoList.add(modelMapper.map(champion_popularite, Champion_populariteDto.class)));
		return dtoList;
	}



	

}
