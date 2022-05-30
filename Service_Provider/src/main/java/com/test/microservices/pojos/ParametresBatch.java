package com.test.microservices.pojos;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("parametresBatch")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ParametresBatch {
	@Id
	public String idMongo;
	@Field("id")
	private int id;
	private int validite_premium;
	private boolean actif;
	private int validite_normal;
	private java.util.Date date_creation;
	private String login;
	
}
