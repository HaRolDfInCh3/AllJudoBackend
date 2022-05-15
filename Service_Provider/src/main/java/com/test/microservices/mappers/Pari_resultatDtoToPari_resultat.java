package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Pari_resultatDto;
import com.test.microservices.pojos.Pari_resultat;
@Service
public class Pari_resultatDtoToPari_resultat implements DtoToObject<Pari_resultatDto,Pari_resultat> {
	DozerBeanMapper modelMapper;
	@Override
	public Pari_resultat dtoToObject(Pari_resultatDto pari_resultatDto) {
		this.modelMapper= new DozerBeanMapper();
		Pari_resultat pari_resultat=modelMapper.map(pari_resultatDto, Pari_resultat.class);
		return pari_resultat;
	}

	@Override
	public Pari_resultatDto objectToDto(Pari_resultat pari_resultat) {
		this.modelMapper= new DozerBeanMapper();
		Pari_resultatDto pari_resultatDto=modelMapper.map(pari_resultat, Pari_resultatDto.class);
		return pari_resultatDto;
	}


	@Override
	public List<Pari_resultatDto> objectsToDtos(List<Pari_resultat> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Pari_resultatDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(pari_resultat -> dtoList.add(modelMapper.map(pari_resultat, Pari_resultatDto.class)));
		return dtoList;
	}



	

}
