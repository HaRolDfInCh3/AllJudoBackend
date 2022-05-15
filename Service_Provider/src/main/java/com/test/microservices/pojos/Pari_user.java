package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("pari_user")
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_user {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public String podium;
	public String premier;
	public java.util.Date date;
	public int user_id;
	public int pari_comp_id;
	@DocumentReference
	private Pari_composition pari_composition2;
	@DocumentReference
	private User user2;
}
