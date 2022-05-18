package com.test.microservices.dto;



import java.util.List;

import com.test.microservices.enums.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_compositionDto {
	private String idMongo;
	public int id;
	public Sexe sexe;
	public String poid;
	public java.util.Date date;
	public String participant;
	public String forfait;
	public String podium_final;
	public String premier_final;
	public int pari;
	List<Pari_compositionElementDto>elements;
}
