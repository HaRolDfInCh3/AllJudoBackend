package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("champion_popularite1")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Champion_popularite1 {
	@Id
	public String idMongo;
	@Field("id")
	public int id;
	public int champion_id;
	@DocumentReference
	private Champion champion2;
	@DocumentReference
	private User user2;
	public int user_id;
	public java.util.Date date;
	public String ip;
	public String user_agent;
	public String host;
}
