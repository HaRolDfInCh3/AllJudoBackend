package com.test.microservices.dto;



import com.test.microservices.enums.TypeChampionAdminExterneJournal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Champion_admin_externe_journalDto {
	public String idMongo;
	public int id;
	public TypeChampionAdminExterneJournal type;
	public java.util.Date date;
	public int user_id;
	public int champion_id;
	public int club_id;
}
