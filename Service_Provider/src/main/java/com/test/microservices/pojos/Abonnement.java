package com.test.microservices.pojos;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("abonnement")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Abonnement {
	@Id
	public String idMongo;
	@Field("id")
	private int id;
	private int user;
	private int champion;
	private java.util.Date date_creation;
	@DocumentReference
	private Champion champion2;
	@DocumentReference
	private User user2;
}
