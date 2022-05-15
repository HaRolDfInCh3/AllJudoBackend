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
@Document("bannieres_par_tailles")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter 
public class Bannieres_par_taille {
	@Id
	public String idMongo;
	@Field("ID")
	public int id;
	public String nom;
	public String code;
	public String image;
	public String url;
	public StatusBanniere actif;
	public String restriction;
	public String taille;
	@Override
	public String toString() {
		return "Bannieres_par_tailles [idMongo=" + idMongo + ", ID=" + id + ", nom=" + nom + ", actif=" + actif
				+ ", restriction=" + restriction + ", taille=" + taille + "]";
	}
}
