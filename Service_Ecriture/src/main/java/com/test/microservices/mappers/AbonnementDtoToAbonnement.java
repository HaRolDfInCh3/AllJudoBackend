package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.AbonnementDto;
import com.test.microservices.pojos.Abonnement;
@Service
public class AbonnementDtoToAbonnement implements DtoToObject<AbonnementDto,Abonnement> {
	DozerBeanMapper modelMapper;
	@Override
	public Abonnement dtoToObject(AbonnementDto abonnementDto) {
		this.modelMapper= new DozerBeanMapper();
		Abonnement abonnement=modelMapper.map(abonnementDto, Abonnement.class);
		return abonnement;
	}

	@Override
	public AbonnementDto objectToDto(Abonnement abonnement) {
		this.modelMapper= new DozerBeanMapper();
		AbonnementDto abonnementDto=modelMapper.map(abonnement, AbonnementDto.class);
		return abonnementDto;
	}

	@Override
	public List<AbonnementDto> objectsToDtos(List<Abonnement> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<AbonnementDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(abonnement -> dtoList.add(modelMapper.map(abonnement, AbonnementDto.class)));
		return dtoList;
	}



	

}
