package com.test.microservices.dto;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ParametresBatchDto {
	
	public String idMongo;
	private boolean actif;
	private int id;
	private int validite_premium;
	private int validite_normal;
	private java.util.Date date_creation;
	private String login;
	
}
