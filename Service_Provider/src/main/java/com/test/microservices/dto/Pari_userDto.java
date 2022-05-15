package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari_userDto {
	private String idMongo;
	public int id;
	public String podium;
	public String premier;
	public java.util.Date date;
	public int user_id;
	public int pari_comp_id;
}
