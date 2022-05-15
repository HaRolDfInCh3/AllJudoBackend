package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Champion_admin_externe_journalDto;
import com.test.microservices.pojos.Champion_admin_externe_journal;
@Service
public class Champion_admin_externe_journalDtoToChampion_admin_externe_journal implements DtoToObject<Champion_admin_externe_journalDto,Champion_admin_externe_journal> {
	DozerBeanMapper modelMapper;
	@Override
	public Champion_admin_externe_journal dtoToObject(Champion_admin_externe_journalDto cAEJournalDto) {
		this.modelMapper= new DozerBeanMapper();
		Champion_admin_externe_journal cAEJournal=modelMapper.map(cAEJournalDto, Champion_admin_externe_journal.class);
		return cAEJournal;
	}

	@Override
	public Champion_admin_externe_journalDto objectToDto(Champion_admin_externe_journal cAEJournal) {
		this.modelMapper= new DozerBeanMapper();
		Champion_admin_externe_journalDto cAEJournalDto=modelMapper.map(cAEJournal, Champion_admin_externe_journalDto.class);
		return cAEJournalDto;
	}


	@Override
	public List<Champion_admin_externe_journalDto> objectsToDtos(List<Champion_admin_externe_journal> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Champion_admin_externe_journalDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(cAEJournal -> dtoList.add(modelMapper.map(cAEJournal, Champion_admin_externe_journalDto.class)));
		return dtoList;
	}



	

}
