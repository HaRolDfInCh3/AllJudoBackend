package com.example.demo.dtos;



import com.example.demo.enums.Designation_role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class RoleDto {
	private String idMongo;
	public int id;
	public Designation_role designation;
}
