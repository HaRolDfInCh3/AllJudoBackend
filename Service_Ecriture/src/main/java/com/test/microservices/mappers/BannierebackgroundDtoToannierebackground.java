package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.BannierebackgroundDto;
import com.test.microservices.pojos.Bannierebackground;
@Service
public class BannierebackgroundDtoToannierebackground implements DtoToObject<BannierebackgroundDto,Bannierebackground> {
	DozerBeanMapper modelMapper;
	@Override
	public Bannierebackground dtoToObject(BannierebackgroundDto bannierebackgroundDto) {
		this.modelMapper= new DozerBeanMapper();
		Bannierebackground bannierebackground=modelMapper.map(bannierebackgroundDto, Bannierebackground.class);
		return bannierebackground;
	}

	@Override
	public BannierebackgroundDto objectToDto(Bannierebackground bannierebackground) {
		this.modelMapper= new DozerBeanMapper();
		BannierebackgroundDto bannierebackgroundDto=modelMapper.map(bannierebackground, BannierebackgroundDto.class);
		return bannierebackgroundDto;
	}


	@Override
	public List<BannierebackgroundDto> objectsToDtos(List<Bannierebackground> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<BannierebackgroundDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(bannierebackground -> dtoList.add(modelMapper.map(bannierebackground, BannierebackgroundDto.class)));
		return dtoList;
	}



	

}
