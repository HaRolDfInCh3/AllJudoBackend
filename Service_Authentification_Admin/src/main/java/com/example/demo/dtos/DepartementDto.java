package com.example.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class DepartementDto {
	private String idMongo;
	private int id;
	public String cP;
	public String nomDepartement;
	public String paysID;
}
