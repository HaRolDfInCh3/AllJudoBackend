package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class GalerieDto {
	private String idMongo;
	private int id;
	public String titre;
	public String titre_en;
	public java.util.Date date;
	public String photographe;
	public int evenement_id;
	public String admin;
	public String lien;
}
