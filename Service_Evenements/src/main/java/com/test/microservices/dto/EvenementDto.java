package com.test.microservices.dto;




import com.test.microservices.enums.CategorieEvenementSexe;
import com.test.microservices.enums.TypeEvenement;
import com.test.microservices.pojos.Evcategorieage;
import com.test.microservices.pojos.Evcategorieevenement;
import com.test.microservices.pojos.Pays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class EvenementDto {
	private String idMongo;
	private int id;
	public String nom;
	public CategorieEvenementSexe sexe;
	public java.util.Date dateDebut;
	public java.util.Date dateFin;
	public java.util.Date datePub;
	public String presentation;
	public Boolean visible;
	public TypeEvenement type;
	public String document1;
	public String document2;
	public String document3;
	public String contact;
	public String telephone;
	public String mail;
	public String web;
	public Boolean valider;
	public Boolean pack;
	public String paysID;
	private Pays pays2;
	private Evcategorieage evcategorieage2;
	private Evcategorieevenement evcategorieevenement2;
	public int categorieID;
	public int categorieageID;
	public int compteur;
}
