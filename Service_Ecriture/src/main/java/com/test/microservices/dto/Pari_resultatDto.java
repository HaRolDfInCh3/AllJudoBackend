package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_resultatDto {
	private String idMongo;
	public int id;
	public int points;
	public int pari_id;
	public int user_id;
}
