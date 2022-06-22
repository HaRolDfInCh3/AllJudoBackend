package com.test.microservices.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Classement_Pays {
private String id; 
private String pays;
private String drapeau;
private int total_premiere_place;
private int total_deuxieme_place;
private int total_troisieme_place;
private int total_cinquieme_place;
private int total_septieme_place;

}
