package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Club_admin_externe_journalDto;
import com.test.microservices.pojos.Club_admin_externe_journal;
@Service
public class Club_admin_externe_journalDtoToClub_admin_externe_journal implements DtoToObject<Club_admin_externe_journalDto,Club_admin_externe_journal> {
	DozerBeanMapper modelMapper;
	@Override
	public Club_admin_externe_journal dtoToObject(Club_admin_externe_journalDto clubAE_journalDto) {
		this.modelMapper= new DozerBeanMapper();
		Club_admin_externe_journal clubAE_journal=modelMapper.map(clubAE_journalDto, Club_admin_externe_journal.class);
		return clubAE_journal;
	}

	@Override
	public Club_admin_externe_journalDto objectToDto(Club_admin_externe_journal clubAE_journal) {
		this.modelMapper= new DozerBeanMapper();
		Club_admin_externe_journalDto clubAE_journalDto=modelMapper.map(clubAE_journal, Club_admin_externe_journalDto.class);
		return clubAE_journalDto;
	}


	@Override
	public List<Club_admin_externe_journalDto> objectsToDtos(List<Club_admin_externe_journal> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Club_admin_externe_journalDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(clubAE_journal -> dtoList.add(modelMapper.map(clubAE_journal, Club_admin_externe_journalDto.class)));
		return dtoList;
	}



	

}
