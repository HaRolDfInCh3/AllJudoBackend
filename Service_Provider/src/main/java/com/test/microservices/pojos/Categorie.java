package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("categorie")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Categorie {
		@Id
		public String idMongo;
		@Field("ID")
		public int id;
		public String nom_categorie;
	
}
