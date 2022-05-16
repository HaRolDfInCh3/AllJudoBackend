package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class NationalityDto {
	private String idMongo;
	public int id;
	public String code_pays;
	public String fr;
	public String en;
}
