package com.test.microservices.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class GroupementResultatClubs {
private int id;
private String equipe;
private List<String> champions;
private List<Integer> championsid;
private int total;
}
