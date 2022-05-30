package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.ParametresBatchDto;
import com.test.microservices.pojos.ParametresBatch;
@Service
public class ParametresBatchDtoToParametresBatch implements DtoToObject<ParametresBatchDto,ParametresBatch> {
	DozerBeanMapper modelMapper;
	@Override
	public ParametresBatch dtoToObject(ParametresBatchDto ParametresBatchDto) {
		this.modelMapper= new DozerBeanMapper();
		ParametresBatch ParametresBatch=modelMapper.map(ParametresBatchDto, ParametresBatch.class);
		return ParametresBatch;
	}

	@Override
	public ParametresBatchDto objectToDto(ParametresBatch ParametresBatch) {
		this.modelMapper= new DozerBeanMapper();
		ParametresBatchDto ParametresBatchDto=modelMapper.map(ParametresBatch, ParametresBatchDto.class);
		return ParametresBatchDto;
	}


	@Override
	public List<ParametresBatchDto> objectsToDtos(List<ParametresBatch> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<ParametresBatchDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(ParametresBatch -> dtoList.add(modelMapper.map(ParametresBatch, ParametresBatchDto.class)));
		return dtoList;
	}



	

}
