package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.test.microservices.enums.TypeClubAdminExterneJournal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document("club_admin_externe_journal")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Club_admin_externe_journal {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public TypeClubAdminExterneJournal type;
	public java.util.Date date;
	@DocumentReference
	private Club club2;
	@DocumentReference
	private User user2;
	public int user_id;
	public int club_id;
}
