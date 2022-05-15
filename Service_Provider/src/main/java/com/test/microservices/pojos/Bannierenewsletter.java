package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.test.microservices.enums.StatusBanniere;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document("bannierenewsletter")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter 
public class Bannierenewsletter {
	@Id
	public String idMongo;
	@Field("ID")
	public int id;
	public String nom;
	public String code;
	public String text;
	public String image;
	public String url;
	public StatusBanniere actif;
}
