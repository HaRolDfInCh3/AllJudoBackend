package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("nationality")
@Data @NoArgsConstructor @AllArgsConstructor
public class Nationality {
	@Id
	private String idMongo;
	@Field("id_pays")
	public int id;
	public String code_pays;
	public String fr;
	public String en;
}
