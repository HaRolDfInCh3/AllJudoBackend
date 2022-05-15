package com.example.ApiDoublons.services;


import java.util.List;



import com.example.ApiDoublons.entities.Champion;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Tache {
	int debut;
	int championID;
	int fin;
	//int blocsize;
	public List<Champion> demarre(){
		
		List<Champion> lc=Singleton.getInstance().getLc();
		Champion champion=Singleton.getInstance().getChampion();
		for(int i=debut;i<fin;i++){
			Champion doublonpotentiel=lc.get(i);
			if(doublonpotentiel.getNom()!=null){
				if(StringSimilarity.similarity(doublonpotentiel.getNom(),champion.getNom())>65  && doublonpotentiel.getId()!=champion.getId()){
					if(Singleton.getInstance().parlots==false){
						Singleton.getInstance().getResultats().add(doublonpotentiel);
					}
					else{
						Singleton.getInstance().getResultats2().get(Singleton.getInstance().getPos().indexOf(championID)).add(doublonpotentiel);
					}
					
			}
			}
			else{
				break;
			}
			
		}
		return lc;
	}
	
}
