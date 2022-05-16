package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Club_horaireDto {
	private String idMongo;
	private int id;
	public int id_club;
	public String jour;
	public String h_deb;
	public String h_fin;
	public String desc;
	public int num_cours;
}
