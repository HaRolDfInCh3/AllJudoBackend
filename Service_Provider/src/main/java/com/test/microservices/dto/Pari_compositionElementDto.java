package com.test.microservices.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_compositionElementDto {
	private String idMongo;
	public int id;
	public String participant;
	public Boolean podium;
	public Boolean premier;
	private int paricompositionid;
	
}
