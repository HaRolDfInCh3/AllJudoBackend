package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.BannierenewsletterDto;
import com.test.microservices.pojos.Bannierenewsletter;
@Service
public class BannierenewsletterDtoToBannierenewsletter implements DtoToObject<BannierenewsletterDto,Bannierenewsletter> {
	DozerBeanMapper modelMapper;
	@Override
	public Bannierenewsletter dtoToObject(BannierenewsletterDto bannierenewsletterDto) {
		this.modelMapper= new DozerBeanMapper();
		Bannierenewsletter bannierenewsletter=modelMapper.map(bannierenewsletterDto, Bannierenewsletter.class);
		return bannierenewsletter;
	}

	@Override
	public BannierenewsletterDto objectToDto(Bannierenewsletter bannierenewsletter) {
		this.modelMapper= new DozerBeanMapper();
		BannierenewsletterDto bannierenewsletterDto=modelMapper.map(bannierenewsletter, BannierenewsletterDto.class);
		return bannierenewsletterDto;
	}


	@Override
	public List<BannierenewsletterDto> objectsToDtos(List<Bannierenewsletter> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<BannierenewsletterDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(bannierenewsletter -> dtoList.add(modelMapper.map(bannierenewsletter, BannierenewsletterDto.class)));
		return dtoList;
	}



	

}
