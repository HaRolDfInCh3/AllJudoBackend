package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.GalerieDto;
import com.test.microservices.pojos.Galerie;
@Service
public class GalerieDtoToGalerie implements DtoToObject<GalerieDto,Galerie> {
	DozerBeanMapper modelMapper;
	@Override
	public Galerie dtoToObject(GalerieDto galerieDto) {
		this.modelMapper= new DozerBeanMapper();
		Galerie galerie=modelMapper.map(galerieDto, Galerie.class);
		return galerie;
	}

	@Override
	public GalerieDto objectToDto(Galerie galerie) {
		this.modelMapper= new DozerBeanMapper();
		GalerieDto galerieDto=modelMapper.map(galerie, GalerieDto.class);
		return galerieDto;
	}


	@Override
	public List<GalerieDto> objectsToDtos(List<Galerie> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<GalerieDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(galerie -> dtoList.add(modelMapper.map(galerie, GalerieDto.class)));
		return dtoList;
	}



	

}
