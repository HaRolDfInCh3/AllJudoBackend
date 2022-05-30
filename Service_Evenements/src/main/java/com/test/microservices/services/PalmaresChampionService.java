package com.test.microservices.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.test.microservices.pojos.Palmares;
import com.test.microservices.repositories.EvresultatRepository;
@Service
public class PalmaresChampionService {
	public PalmaresChampionService(EvresultatRepository eRepo) {
		this.eRepo = eRepo;
	}
	EvresultatRepository eRepo;
	public List<Palmares> getResults() {
		return this.eRepo.aggregateBySample();
		
	}
	
	
	}