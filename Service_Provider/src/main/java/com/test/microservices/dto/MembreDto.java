package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class MembreDto {
	private String idMongo;
	public int id;
	public int user_id;
	public String date_naissance;
	public java.util.Date date_naissance2;
	public String grade;
	public String ville;
	public String cP;
	public String pays;
	public String gcoo1;
	public String gcoo2;
	public String gaddress;
}
