package com.test.microservices.dto;



import com.test.microservices.enums.TypeClubAdminExterneJournal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Club_admin_externe_journalDto {
	private String idMongo;
	private int id;
	public TypeClubAdminExterneJournal type;
	public java.util.Date date;
	public int user_id;
	public int club_id;
}
