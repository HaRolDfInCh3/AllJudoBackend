package com.test.microservices.dto;



import com.test.microservices.pojos.Evenementimportant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class EvenementimportantdirectDto {
	private String idMongo;
	private int id;
	public java.util.Date date;
	public String admin;
	public String titre;
	public String texte;
	public int evenement_important_id;
	private Evenementimportant evenementimportant2;
	public Boolean une;
}
