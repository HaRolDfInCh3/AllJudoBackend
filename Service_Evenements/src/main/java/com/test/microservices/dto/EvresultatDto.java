package com.test.microservices.dto;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class EvresultatDto {
	private String idMongo;
	private int id;
	public String rang;
	public String commentaire;
	public String club;
	public int evenementID;
	public int championID;
	public int equipeID;
	private EvenementDto evenement2;
	private ChampionDto champion2;
	private EvequipeDto evequipe2;
	public String poidID;
}
