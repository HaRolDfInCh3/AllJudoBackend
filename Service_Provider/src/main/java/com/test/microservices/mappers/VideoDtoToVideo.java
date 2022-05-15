package com.test.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.test.microservices.dto.VideoDto;
import com.test.microservices.pojos.Video;
@Service
public class VideoDtoToVideo implements DtoToObject<VideoDto,Video> {
	DozerBeanMapper modelMapper;
	@Override
	public Video dtoToObject(VideoDto videoDto) {
		this.modelMapper= new DozerBeanMapper();
		Video video=modelMapper.map(videoDto, Video.class);
		return video;
	}

	@Override
	public VideoDto objectToDto(Video video) {
		this.modelMapper= new DozerBeanMapper();
		VideoDto videoDto=modelMapper.map(video, VideoDto.class);
		return videoDto;
	}


	@Override
	public List<VideoDto> objectsToDtos(List<Video> objectList) {
		this.modelMapper= new DozerBeanMapper();
		List<VideoDto>dtoList=new ArrayList<>();
		objectList.stream().forEach(video -> dtoList.add(modelMapper.map(video, VideoDto.class)));
		return dtoList;
	}



	

}
