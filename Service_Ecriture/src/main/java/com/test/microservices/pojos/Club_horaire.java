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

@Document("club_horaires")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Club_horaire {
	@Id
	private String idMongo;
	@Field("id")
	private int id;
	public int id_club;
	@DocumentReference
	private Club club2;
	public String jour;
	public String h_deb;
	public String h_fin;
	public String Desc;
	public int num_cours;
}
