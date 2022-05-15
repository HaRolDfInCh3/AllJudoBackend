package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PaysDto;
import com.example.demo.entities.Pays;
@Service
public class PaysDtoToPays implements DtoToObject<PaysDto,Pays> {
	DozerBeanMapper modelMapper;
	@Override
	public Pays dtoToObject(PaysDto paysDto) {
		this.modelMapper= new DozerBeanMapper();
		Pays pays=modelMapper.map(paysDto, Pays.class);
		return pays;
	}

	@Override
	public PaysDto objectToDto(Pays pays) {
		this.modelMapper= new DozerBeanMapper();
		PaysDto paysDto=modelMapper.map(pays, PaysDto.class);
		return paysDto;
	}


	@Override
	public List<PaysDto> objectsToDtos(List<Pays> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<PaysDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(pays -> dtoList.add(modelMapper.map(pays, PaysDto.class)));
		return dtoList;
	}



	

}
