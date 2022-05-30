package com.example.ApiDoublons.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.*;

import lombok.*;
@Data @NoArgsConstructor  
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListeDeDoublons {
	@JsonProperty("id")
	private int id;
	@JsonProperty("champion")
	private Champion champion;
	@JsonProperty("doublons")
	private List<Champion>doublons;
	
}
