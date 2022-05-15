package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.DepartementDto;
import com.test.microservices.pojos.Departement;
@Service
public class DepartementDtoToDepartement implements DtoToObject<DepartementDto,Departement> {
	DozerBeanMapper modelMapper;
	@Override
	public Departement dtoToObject(DepartementDto departementDto) {
		this.modelMapper= new DozerBeanMapper();
		Departement departement=modelMapper.map(departementDto, Departement.class);
		return departement;
	}

	@Override
	public DepartementDto objectToDto(Departement departement) {
		this.modelMapper= new DozerBeanMapper();
		DepartementDto departementDto=modelMapper.map(departement, DepartementDto.class);
		return departementDto;
	}


	@Override
	public List<DepartementDto> objectsToDtos(List<Departement> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<DepartementDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(departement -> dtoList.add(modelMapper.map(departement, DepartementDto.class)));
		return dtoList;
	}



	

}
