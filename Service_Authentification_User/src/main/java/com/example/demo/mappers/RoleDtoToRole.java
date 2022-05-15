package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.RoleDto;
import com.example.demo.entities.Role;
@Service
public class RoleDtoToRole implements DtoToObject<RoleDto,Role> {
	DozerBeanMapper modelMapper;
	@Override
	public Role dtoToObject(RoleDto clubDto) {
		this.modelMapper= new DozerBeanMapper();
		Role club=modelMapper.map(clubDto, Role.class);
		return club;
	}

	@Override
	public RoleDto objectToDto(Role club) {
		this.modelMapper= new DozerBeanMapper();
		RoleDto clubDto=modelMapper.map(club, RoleDto.class);
		return clubDto;
	}


	@Override
	public List<RoleDto> objectsToDtos(List<Role> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<RoleDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(club -> dtoList.add(modelMapper.map(club, RoleDto.class)));
		return dtoList;
	}



	

}
