package com.test.microservices.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Resultat_Pays {
	private String rang;
	private String nom;
	private String pays;
}
