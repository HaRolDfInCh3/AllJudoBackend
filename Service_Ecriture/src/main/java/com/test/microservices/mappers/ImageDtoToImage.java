package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.ImageDto;
import com.test.microservices.pojos.Image;
@Service
public class ImageDtoToImage implements DtoToObject<ImageDto,Image> {
	DozerBeanMapper modelMapper;
	@Override
	public Image dtoToObject(ImageDto imageDto) {
		this.modelMapper= new DozerBeanMapper();
		Image image=modelMapper.map(imageDto, Image.class);
		return image;
	}

	@Override
	public ImageDto objectToDto(Image image) {
		this.modelMapper= new DozerBeanMapper();
		ImageDto imageDto=modelMapper.map(image, ImageDto.class);
		return imageDto;
	}


	@Override
	public List<ImageDto> objectsToDtos(List<Image> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<ImageDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(image -> dtoList.add(modelMapper.map(image, ImageDto.class)));
		return dtoList;
	}



	

}
