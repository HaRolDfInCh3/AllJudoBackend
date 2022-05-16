package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.LiensDto;
import com.test.microservices.pojos.Liens;
@Service
public class LiensDtoToLiens implements DtoToObject<LiensDto,Liens> {
	DozerBeanMapper modelMapper;
	@Override
	public Liens dtoToObject(LiensDto liensDto) {
		this.modelMapper= new DozerBeanMapper();
		Liens liens=modelMapper.map(liensDto, Liens.class);
		return liens;
	}

	@Override
	public LiensDto objectToDto(Liens liens) {
		this.modelMapper= new DozerBeanMapper();
		LiensDto liensDto=modelMapper.map(liens, LiensDto.class);
		return liensDto;
	}


	@Override
	public List<LiensDto> objectsToDtos(List<Liens> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<LiensDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(liens -> dtoList.add(modelMapper.map(liens, LiensDto.class)));
		return dtoList;
	}



	

}
