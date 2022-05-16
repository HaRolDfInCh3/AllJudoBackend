package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class TechniqueDto {
	private String idMongo;
	public int id;
	public String nom;
	public String famille;
	public String presentation;
	public String presentation_en;
	public String conseils;
	public String conseils_en;
}
