package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ArticleDto {
	private String idMongo;
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
