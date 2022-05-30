package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PalmaresDto {
	private String Evenement;
	private int nbr_1ere_place;
	private int nbr_2eme_place;
	private int nbr_3eme_place;
}
