package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class FormulaireDto {
	private String idMongo;
	public int id;
	public String nom;
	public String prenom;
	public String mail;
	public String tel;
	public String club;
	public String fonction;
	public String participants;
	public String ages;
	public String sexe;
	public String periode;
	public String budget;
	public String activite;
	public String description;
	public int cadets;
	public int juniors;
	public int seniors;
	public String active2;
	public String active3;
}
