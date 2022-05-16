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

@Document("club_judokas")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Club_judoka {
	@Id
	private String idMongo;
	@Field("id_club_judo")
	public int id;
	public int id_club;
	public int id_judoka;
	public String nom_judoka;
	@DocumentReference
	private Club club2;
	@DocumentReference
	private Champion champion2;
}
