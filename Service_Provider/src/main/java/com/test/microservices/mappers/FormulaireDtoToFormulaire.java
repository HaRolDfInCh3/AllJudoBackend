package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.FormulaireDto;
import com.test.microservices.pojos.Formulaire;
@Service
public class FormulaireDtoToFormulaire implements DtoToObject<FormulaireDto,Formulaire> {
	DozerBeanMapper modelMapper;
	@Override
	public Formulaire dtoToObject(FormulaireDto formulaireDto) {
		this.modelMapper= new DozerBeanMapper();
		Formulaire formulaire=modelMapper.map(formulaireDto, Formulaire.class);
		return formulaire;
	}

	@Override
	public FormulaireDto objectToDto(Formulaire formulaire) {
		this.modelMapper= new DozerBeanMapper();
		FormulaireDto formulaireDto=modelMapper.map(formulaire, FormulaireDto.class);
		return formulaireDto;
	}


	@Override
	public List<FormulaireDto> objectsToDtos(List<Formulaire> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<FormulaireDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(formulaire -> dtoList.add(modelMapper.map(formulaire, FormulaireDto.class)));
		return dtoList;
	}



	

}
