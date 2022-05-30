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
@Document("commentaires")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Commentaire {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public java.util.Date date;
	public String commentaire;
	public Boolean valide;
	public int user_id;
	public int news_id;
	public int video_id;
	public int champion_id;
	public String positif;
	public String negatif;
	private User user2;
	@DocumentReference(lazy = true)
	private Video video2;
	private Champion champion2;
	@DocumentReference(lazy = true)
	private News news2;
}
