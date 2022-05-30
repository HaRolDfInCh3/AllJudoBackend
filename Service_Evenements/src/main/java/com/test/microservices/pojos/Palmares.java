package com.test.microservices.pojos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Palmares {
	private int championid;
	private String nom;
	private int equipe;
	private String categorie_age;
	private String categorie_evenement;
	private int total1er;
	private int total2eme;
	private int total3eme;
	
	
}
