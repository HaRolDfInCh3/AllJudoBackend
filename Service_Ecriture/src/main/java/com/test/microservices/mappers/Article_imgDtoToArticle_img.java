package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.Article_imgDto;
import com.test.microservices.pojos.Article_img;
@Service
public class Article_imgDtoToArticle_img implements DtoToObject<Article_imgDto,Article_img> {
	DozerBeanMapper modelMapper;
	@Override
	public Article_img dtoToObject(Article_imgDto article_imgDto) {
		this.modelMapper= new DozerBeanMapper();
		Article_img article_img=modelMapper.map(article_imgDto, Article_img.class);
		return article_img;
	}

	@Override
	public Article_imgDto objectToDto(Article_img article_img) {
		this.modelMapper= new DozerBeanMapper();
		Article_imgDto article_imgDto=modelMapper.map(article_img, Article_imgDto.class);
		return article_imgDto;
	}


	@Override
	public List<Article_imgDto> objectsToDtos(List<Article_img> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<Article_imgDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(article_img -> dtoList.add(modelMapper.map(article_img, Article_imgDto.class)));
		return dtoList;
	}



	

}
