package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.MembreDto;
import com.test.microservices.pojos.Membre;
@Service
public class MembreDtoToMembre implements DtoToObject<MembreDto,Membre> {
	DozerBeanMapper modelMapper;
	@Override
	public Membre dtoToObject(MembreDto membreDto) {
		this.modelMapper= new DozerBeanMapper();
		Membre membre=modelMapper.map(membreDto, Membre.class);
		return membre;
	}

	@Override
	public MembreDto objectToDto(Membre membre) {
		this.modelMapper= new DozerBeanMapper();
		MembreDto membreDto=modelMapper.map(membre, MembreDto.class);
		return membreDto;
	}


	@Override
	public List<MembreDto> objectsToDtos(List<Membre> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<MembreDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(membre -> dtoList.add(modelMapper.map(membre, MembreDto.class)));
		return dtoList;
	}



	

}
