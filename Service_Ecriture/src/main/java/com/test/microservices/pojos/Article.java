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

@Document("article")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Article {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public String nom;
	public Double prix;
	public String offre;
	public String marque;
	public int livraison;
	public String transporteur;
	public String descr;
	public String image_pre;
	public String video;
	public String type;
	public String code_paypal;
	public Double old_prix;
}
