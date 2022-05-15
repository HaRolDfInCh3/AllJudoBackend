package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Bannieres_par_tailleDto;
import com.test.microservices.pojos.Bannieres_par_taille;
@Service
public class Bannieres_par_tailleDtoToBannieres_par_taille implements DtoToObject<Bannieres_par_tailleDto,Bannieres_par_taille> {
	DozerBeanMapper modelMapper;
	@Override
	public Bannieres_par_taille dtoToObject(Bannieres_par_tailleDto bannieres_par_tailleDto) {
		this.modelMapper= new DozerBeanMapper();
		Bannieres_par_taille bannieres_par_taille=modelMapper.map(bannieres_par_tailleDto, Bannieres_par_taille.class);
		return bannieres_par_taille;
	}

	@Override
	public Bannieres_par_tailleDto objectToDto(Bannieres_par_taille bannieres_par_taille) {
		this.modelMapper= new DozerBeanMapper();
		Bannieres_par_tailleDto bannieres_par_tailleDto=modelMapper.map(bannieres_par_taille, Bannieres_par_tailleDto.class);
		return bannieres_par_tailleDto;
	}


	@Override
	public List<Bannieres_par_tailleDto> objectsToDtos(List<Bannieres_par_taille> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Bannieres_par_tailleDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(bannieres_par_taille -> dtoList.add(modelMapper.map(bannieres_par_taille, Bannieres_par_tailleDto.class)));
		return dtoList;
	}



	

}
