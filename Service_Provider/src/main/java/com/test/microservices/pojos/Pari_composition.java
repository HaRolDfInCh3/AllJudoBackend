package com.test.microservices.pojos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.test.microservices.enums.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document("pari_composition")
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_composition {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public Sexe sexe;
	public String poid;
	public java.util.Date date;
	public String participant;
	public String forfait;
	public String podium_final;
	public String premier_final;
	@Field("pari_id")
	public int pari;
	@DocumentReference
	private Pari pari2;
	@DocumentReference
	List<Pari_compositionElement>elements;
}
