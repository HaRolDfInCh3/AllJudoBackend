package com.example.demo.dtos;



import com.example.demo.enums.PermissionAdmin;


import lombok.*;
@Data @NoArgsConstructor @AllArgsConstructor
public class AdminDto {
	public String idMongo;
	public int adminId;
	public String login;
	public PermissionAdmin permission;
	public String password;
}
