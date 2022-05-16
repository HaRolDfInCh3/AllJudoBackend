package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbonnementDto {
	public String idMongo;
	private int id;
	public int user;
	public int champion;
	public java.util.Date date_creation;
}
