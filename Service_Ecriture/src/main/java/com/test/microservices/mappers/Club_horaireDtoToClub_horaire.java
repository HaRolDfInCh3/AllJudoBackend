package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Club_horaireDto;
import com.test.microservices.pojos.Club_horaire;
@Service
public class Club_horaireDtoToClub_horaire implements DtoToObject<Club_horaireDto,Club_horaire> {
	DozerBeanMapper modelMapper;
	@Override
	public Club_horaire dtoToObject(Club_horaireDto club_horaireDto) {
		this.modelMapper= new DozerBeanMapper();
		Club_horaire club_horaire=modelMapper.map(club_horaireDto, Club_horaire.class);
		return club_horaire;
	}

	@Override
	public Club_horaireDto objectToDto(Club_horaire club_horaire) {
		this.modelMapper= new DozerBeanMapper();
		Club_horaireDto club_horaireDto=modelMapper.map(club_horaire, Club_horaireDto.class);
		return club_horaireDto;
	}


	@Override
	public List<Club_horaireDto> objectsToDtos(List<Club_horaire> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Club_horaireDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(club_horaire -> dtoList.add(modelMapper.map(club_horaire, Club_horaireDto.class)));
		return dtoList;
	}



	

}
