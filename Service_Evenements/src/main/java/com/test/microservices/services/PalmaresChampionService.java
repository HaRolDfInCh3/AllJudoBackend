package com.test.microservices.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.test.microservices.pojos.Classement_Clubs;
import com.test.microservices.pojos.Classement_Pays;
import com.test.microservices.pojos.GroupementResultatPays;
import com.test.microservices.pojos.Palmares;
import com.test.microservices.repositories.EvresultatRepository;
@Service
public class PalmaresChampionService {
	public PalmaresChampionService(EvresultatRepository eRepo) {
		this.eRepo = eRepo;
	}
	EvresultatRepository eRepo;
	public List<Classement_Pays> getResults() {
		return this.eRepo.getClassementPaysParEvenementID(6017);
		
	}
	
	
	}