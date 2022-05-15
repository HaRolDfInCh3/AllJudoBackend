package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.ArticleDto;
import com.test.microservices.pojos.Article;
@Service
public class ArticleDtoToArticle implements DtoToObject<ArticleDto,Article> {
	DozerBeanMapper modelMapper;
	@Override
	public Article dtoToObject(ArticleDto articleDto) {
		this.modelMapper= new DozerBeanMapper();
		Article article=modelMapper.map(articleDto, Article.class);
		return article;
	}

	@Override
	public ArticleDto objectToDto(Article article) {
		this.modelMapper= new DozerBeanMapper();
		ArticleDto articleDto=modelMapper.map(article, ArticleDto.class);
		return articleDto;
	}


	@Override
	public List<ArticleDto> objectsToDtos(List<Article> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<ArticleDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(article -> dtoList.add(modelMapper.map(article, ArticleDto.class)));
		return dtoList;
	}



	

}
