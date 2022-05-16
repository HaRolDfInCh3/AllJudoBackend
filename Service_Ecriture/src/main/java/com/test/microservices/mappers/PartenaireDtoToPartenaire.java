package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.PartenaireDto;
import com.test.microservices.pojos.Partenaire;
@Service
public class PartenaireDtoToPartenaire implements DtoToObject<PartenaireDto,Partenaire> {
	DozerBeanMapper modelMapper;
	@Override
	public Partenaire dtoToObject(PartenaireDto partenaireDto) {
		this.modelMapper= new DozerBeanMapper();
		Partenaire partenaire=modelMapper.map(partenaireDto, Partenaire.class);
		return partenaire;
	}

	@Override
	public PartenaireDto objectToDto(Partenaire partenaire) {
		this.modelMapper= new DozerBeanMapper();
		PartenaireDto partenaireDto=modelMapper.map(partenaire, PartenaireDto.class);
		return partenaireDto;
	}


	@Override
	public List<PartenaireDto> objectsToDtos(List<Partenaire> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<PartenaireDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(partenaire -> dtoList.add(modelMapper.map(partenaire, PartenaireDto.class)));
		return dtoList;
	}



	

}
