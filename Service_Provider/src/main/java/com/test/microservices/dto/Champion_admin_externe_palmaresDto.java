package com.test.microservices.dto;


import com.test.microservices.enums.IntituleCompetitionFrancais;
import com.test.microservices.enums.TypeCompetition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Champion_admin_externe_palmaresDto {
	public String idMongo;
	public int id;
	public String rang;
	public int championID;
	public String poidsID;
	public java.util.Date date;
	public String categorieAge;
	public TypeCompetition competitionType;
	public String competitionLieu;
	public int competitionDepID;
	public int competitionRegID;
	public IntituleCompetitionFrancais competitionFr;
}
