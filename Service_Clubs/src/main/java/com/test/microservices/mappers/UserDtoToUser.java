package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.UserDto;
import com.test.microservices.pojos.User;
@Service
public class UserDtoToUser implements DtoToObject<UserDto,User> {
	DozerBeanMapper modelMapper;
	@Override
	public User dtoToObject(UserDto userDto) {
		this.modelMapper= new DozerBeanMapper();
		User user=modelMapper.map(userDto, User.class);
		return user;
	}

	@Override
	public UserDto objectToDto(User user) {
		this.modelMapper= new DozerBeanMapper();
		UserDto userDto=modelMapper.map(user, UserDto.class);
		return userDto;
	}


	@Override
	public List<UserDto> objectsToDtos(List<User> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<UserDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(user -> dtoList.add(modelMapper.map(user, UserDto.class)));
		return dtoList;
	}



	

}
