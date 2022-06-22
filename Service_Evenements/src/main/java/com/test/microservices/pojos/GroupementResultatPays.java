package com.test.microservices.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class GroupementResultatPays {
private int id;
private int categoriepoids;
private List<Resultat_Pays> champions;
}
