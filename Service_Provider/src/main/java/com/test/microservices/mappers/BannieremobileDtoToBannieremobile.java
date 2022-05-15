package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.BannieremobileDto;
import com.test.microservices.pojos.Bannieremobile;
@Service
public class BannieremobileDtoToBannieremobile implements DtoToObject<BannieremobileDto,Bannieremobile> {
	DozerBeanMapper modelMapper;
	@Override
	public Bannieremobile dtoToObject(BannieremobileDto bannieremobileDto) {
		this.modelMapper= new DozerBeanMapper();
		Bannieremobile bannieremobile=modelMapper.map(bannieremobileDto, Bannieremobile.class);
		return bannieremobile;
	}

	@Override
	public BannieremobileDto objectToDto(Bannieremobile bannieremobile) {
		this.modelMapper= new DozerBeanMapper();
		BannieremobileDto bannieremobileDto=modelMapper.map(bannieremobile, BannieremobileDto.class);
		return bannieremobileDto;
	}


	@Override
	public List<BannieremobileDto> objectsToDtos(List<Bannieremobile> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<BannieremobileDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(bannieremobile -> dtoList.add(modelMapper.map(bannieremobile, BannieremobileDto.class)));
		return dtoList;
	}



	

}
