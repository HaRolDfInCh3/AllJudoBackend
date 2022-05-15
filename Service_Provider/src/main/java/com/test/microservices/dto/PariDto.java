package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PariDto {
	private String idMongo;
	public int id;
	public java.util.Date date_debut;
	public java.util.Date date_fin;
	public String description;
	public Boolean actif;
	public Boolean corrige;
	public int evenement_id;
}
