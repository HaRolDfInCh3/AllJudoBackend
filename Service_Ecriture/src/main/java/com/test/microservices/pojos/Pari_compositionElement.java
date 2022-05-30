package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document("pari_compositionElement")
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_compositionElement {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public String participant;
	public Boolean podium;
	public Boolean premier;
	private int paricompositionid;
	
}
