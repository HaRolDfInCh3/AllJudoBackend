package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Champion_populariteDto;
import com.test.microservices.pojos.Champion_popularite1;
@Service
public class Champion_populariteDtoToChampion_popularite1 implements DtoToObject<Champion_populariteDto,Champion_popularite1> {
	DozerBeanMapper modelMapper;
	@Override
	public Champion_popularite1 dtoToObject(Champion_populariteDto champion_popularite1Dto) {
		this.modelMapper= new DozerBeanMapper();
		Champion_popularite1 champion_popularite1=modelMapper.map(champion_popularite1Dto, Champion_popularite1.class);
		return champion_popularite1;
	}

	@Override
	public Champion_populariteDto objectToDto(Champion_popularite1 champion_popularite1) {
		this.modelMapper= new DozerBeanMapper();
		Champion_populariteDto champion_popularite1Dto=modelMapper.map(champion_popularite1, Champion_populariteDto.class);
		return champion_popularite1Dto;
	}


	@Override
	public List<Champion_populariteDto> objectsToDtos(List<Champion_popularite1> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Champion_populariteDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(champion_popularite1 -> dtoList.add(modelMapper.map(champion_popularite1, Champion_populariteDto.class)));
		return dtoList;
	}



	

}
