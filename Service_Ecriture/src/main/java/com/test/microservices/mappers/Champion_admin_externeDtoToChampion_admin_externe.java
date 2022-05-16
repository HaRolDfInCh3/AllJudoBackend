package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Champion_admin_externeDto;
import com.test.microservices.pojos.Champion_admin_externe;
@Service
public class Champion_admin_externeDtoToChampion_admin_externe implements DtoToObject<Champion_admin_externeDto,Champion_admin_externe> {
	DozerBeanMapper modelMapper;
	@Override
	public Champion_admin_externe dtoToObject(Champion_admin_externeDto cAEDto) {
		this.modelMapper= new DozerBeanMapper();
		Champion_admin_externe cAE=modelMapper.map(cAEDto, Champion_admin_externe.class);
		return cAE;
	}

	@Override
	public Champion_admin_externeDto objectToDto(Champion_admin_externe cAE) {
		this.modelMapper= new DozerBeanMapper();
		Champion_admin_externeDto cAEDto=modelMapper.map(cAE, Champion_admin_externeDto.class);
		return cAEDto;
	}


	@Override
	public List<Champion_admin_externeDto> objectsToDtos(List<Champion_admin_externe> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Champion_admin_externeDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(cAE -> dtoList.add(modelMapper.map(cAE, Champion_admin_externeDto.class)));
		return dtoList;
	}



	

}
