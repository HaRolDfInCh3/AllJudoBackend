package com.test.microservices.pojos;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.test.microservices.enums.IntituleCompetitionFrancais;
import com.test.microservices.enums.TypeCompetition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Document("champion_admin_externe_palmares")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Champion_admin_externe_palmares {
	@Id
	public String idMongo;
	@Field("ID")
	public int id;
	@DocumentReference
	private Champion champion2;
	@DocumentReference
	private Departement departement2;
	@DocumentReference
	private Evcategorieage evcategorieage2;
	@DocumentReference
	private Region region2;
	public String Rang;
	public int ChampionID;
	public String PoidsID;
	public Date date;
	public int CategorieAge;
	public TypeCompetition CompetitionType;
	public String CompetitionLieu;
	public int CompetitionDepID;
	public int CompetitionRegID;
	public IntituleCompetitionFrancais CompetitionFr;
}
