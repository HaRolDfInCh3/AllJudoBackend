package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("regions")
@Data @NoArgsConstructor @AllArgsConstructor
public class Region {
	@Id
	private String idMongo;
	@Field("ID")
	public int id;
	public String NomRegion;
}
