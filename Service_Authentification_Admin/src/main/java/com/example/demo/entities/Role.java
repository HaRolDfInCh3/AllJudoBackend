package com.example.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.enums.Designation_role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document("roles")
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public Designation_role designation;
}
