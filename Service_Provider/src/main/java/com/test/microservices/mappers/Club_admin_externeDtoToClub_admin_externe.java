package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Club_admin_externeDto;
import com.test.microservices.pojos.Club_admin_externe;
@Service
public class Club_admin_externeDtoToClub_admin_externe implements DtoToObject<Club_admin_externeDto,Club_admin_externe> {
	DozerBeanMapper modelMapper;
	@Override
	public Club_admin_externe dtoToObject(Club_admin_externeDto club_admin_externeDto) {
		this.modelMapper= new DozerBeanMapper();
		Club_admin_externe club_admin_externe=modelMapper.map(club_admin_externeDto, Club_admin_externe.class);
		return club_admin_externe;
	}

	@Override
	public Club_admin_externeDto objectToDto(Club_admin_externe club_admin_externe) {
		this.modelMapper= new DozerBeanMapper();
		Club_admin_externeDto club_admin_externeDto=modelMapper.map(club_admin_externe, Club_admin_externeDto.class);
		return club_admin_externeDto;
	}


	@Override
	public List<Club_admin_externeDto> objectsToDtos(List<Club_admin_externe> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Club_admin_externeDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(club_admin_externe -> dtoList.add(modelMapper.map(club_admin_externe, Club_admin_externeDto.class)));
		return dtoList;
	}



	

}
