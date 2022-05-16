package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Champion_admin_externe_palmaresDto;
import com.test.microservices.pojos.Champion_admin_externe_palmares;
@Service
public class Champion_admin_externe_palmaresDtoToChampion_admin_externe_palmares implements DtoToObject<Champion_admin_externe_palmaresDto,Champion_admin_externe_palmares> {
	DozerBeanMapper modelMapper;
	@Override
	public Champion_admin_externe_palmares dtoToObject(Champion_admin_externe_palmaresDto cAEPalmaresDto) {
		this.modelMapper= new DozerBeanMapper();
		Champion_admin_externe_palmares cAEPalmares=modelMapper.map(cAEPalmaresDto, Champion_admin_externe_palmares.class);
		return cAEPalmares;
	}

	@Override
	public Champion_admin_externe_palmaresDto objectToDto(Champion_admin_externe_palmares cAEPalmares) {
		this.modelMapper= new DozerBeanMapper();
		Champion_admin_externe_palmaresDto cAEPalmaresDto=modelMapper.map(cAEPalmares, Champion_admin_externe_palmaresDto.class);
		return cAEPalmaresDto;
	}


	@Override
	public List<Champion_admin_externe_palmaresDto> objectsToDtos(List<Champion_admin_externe_palmares> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Champion_admin_externe_palmaresDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(cAEPalmares -> dtoList.add(modelMapper.map(cAEPalmares, Champion_admin_externe_palmaresDto.class)));
		return dtoList;
	}



	

}
