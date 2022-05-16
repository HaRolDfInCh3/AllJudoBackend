package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.LiensDto;
import com.test.microservices.pojos.Liens2;
@Service
public class LiensDtoToLiens2 implements DtoToObject<LiensDto,Liens2> {
	DozerBeanMapper modelMapper;
	@Override
	public Liens2 dtoToObject(LiensDto liens2Dto) {
		this.modelMapper= new DozerBeanMapper();
		Liens2 liens2=modelMapper.map(liens2Dto, Liens2.class);
		return liens2;
	}

	@Override
	public LiensDto objectToDto(Liens2 liens2) {
		this.modelMapper= new DozerBeanMapper();
		LiensDto liens2Dto=modelMapper.map(liens2, LiensDto.class);
		return liens2Dto;
	}


	@Override
	public List<LiensDto> objectsToDtos(List<Liens2> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<LiensDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(liens2 -> dtoList.add(modelMapper.map(liens2, LiensDto.class)));
		return dtoList;
	}



	

}
