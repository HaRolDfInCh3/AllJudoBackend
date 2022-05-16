package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Club_judokaDto;
import com.test.microservices.pojos.Club_judoka;
@Service
public class Club_judokaDtoToClub_judoka implements DtoToObject<Club_judokaDto,Club_judoka> {
	DozerBeanMapper modelMapper;
	@Override
	public Club_judoka dtoToObject(Club_judokaDto club_judokaDto) {
		this.modelMapper= new DozerBeanMapper();
		Club_judoka club_judoka=modelMapper.map(club_judokaDto, Club_judoka.class);
		return club_judoka;
	}

	@Override
	public Club_judokaDto objectToDto(Club_judoka club_judoka) {
		this.modelMapper= new DozerBeanMapper();
		Club_judokaDto club_judokaDto=modelMapper.map(club_judoka, Club_judokaDto.class);
		return club_judokaDto;
	}


	@Override
	public List<Club_judokaDto> objectsToDtos(List<Club_judoka> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Club_judokaDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(club_judoka -> dtoList.add(modelMapper.map(club_judoka, Club_judokaDto.class)));
		return dtoList;
	}



	

}
