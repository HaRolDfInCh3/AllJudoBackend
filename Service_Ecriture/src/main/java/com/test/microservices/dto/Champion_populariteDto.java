package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Champion_populariteDto {
	public String idMongo;
	public int id;
	public int champion_id;
	public int user_id;
	public java.util.Date date;
	public String ip;
	public String user_agent;
	public String host;
}
