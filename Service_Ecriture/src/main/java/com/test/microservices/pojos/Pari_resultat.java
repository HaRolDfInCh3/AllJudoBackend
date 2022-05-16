package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("pari_resultat")
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_resultat {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public int points;
	public int pari_id;
	public int user_id;
	@DocumentReference
	private Pari pari2;
	@DocumentReference
	private User user2;
}
