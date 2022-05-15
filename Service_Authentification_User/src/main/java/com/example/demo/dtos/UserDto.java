package com.example.demo.dtos;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDto {
	private String idMongo;
	public int id;
	public String nom;
	public String prenom;
	public String username;
	public String email;
	public String date_naissance;
	public java.util.Date date_naissance2;
	public String code_postale;
	public String ville;
	public String pays;
	public String grade;
	private ClubDto club2;
	private PaysDto pays2;
	public String club;
	public String user_ip;
	public Boolean newsletter;
	public Boolean offres;
	public String gcoo1;
	public String gcoo2;
	public String gaddress;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String password;
	public int user_regdate;
	private List<RoleDto> roles;
}
