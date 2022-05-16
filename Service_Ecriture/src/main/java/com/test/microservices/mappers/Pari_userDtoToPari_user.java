package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Pari_userDto;
import com.test.microservices.pojos.Pari_user;
@Service
public class Pari_userDtoToPari_user implements DtoToObject<Pari_userDto,Pari_user> {
	DozerBeanMapper modelMapper;
	@Override
	public Pari_user dtoToObject(Pari_userDto pari_userDto) {
		this.modelMapper= new DozerBeanMapper();
		Pari_user pari_user=modelMapper.map(pari_userDto, Pari_user.class);
		return pari_user;
	}

	@Override
	public Pari_userDto objectToDto(Pari_user pari_user) {
		this.modelMapper= new DozerBeanMapper();
		Pari_userDto pari_userDto=modelMapper.map(pari_user, Pari_userDto.class);
		return pari_userDto;
	}


	@Override
	public List<Pari_userDto> objectsToDtos(List<Pari_user> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Pari_userDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(pari_user -> dtoList.add(modelMapper.map(pari_user, Pari_userDto.class)));
		return dtoList;
	}



	

}
