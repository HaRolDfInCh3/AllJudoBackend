package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.RegionDto;
import com.test.microservices.pojos.Region;
@Service
public class RegionDtoToRegion implements DtoToObject<RegionDto,Region> {
	DozerBeanMapper modelMapper;
	@Override
	public Region dtoToObject(RegionDto regionDto) {
		this.modelMapper= new DozerBeanMapper();
		Region region=modelMapper.map(regionDto, Region.class);
		return region;
	}

	@Override
	public RegionDto objectToDto(Region region) {
		this.modelMapper= new DozerBeanMapper();
		RegionDto regionDto=modelMapper.map(region, RegionDto.class);
		return regionDto;
	}


	@Override
	public List<RegionDto> objectsToDtos(List<Region> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<RegionDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(region -> dtoList.add(modelMapper.map(region, RegionDto.class)));
		return dtoList;
	}



	

}
