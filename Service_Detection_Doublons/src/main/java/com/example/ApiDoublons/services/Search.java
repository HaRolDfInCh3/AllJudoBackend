package com.example.ApiDoublons.services;





public class Search {
	
	static int taille;
	static int blocsize=10000;
	public static int nbrdethreads;
	
	static{
		
		taille=(int) Singleton.getInstance().getLc().size();
		nbrdethreads=(int)(taille/blocsize)+1;
		
		
	}
	public static void getSimilar(int id){
		System.out.println(nbrdethreads+" threads");
		int debut=0;
		for(int i=0;i<nbrdethreads;i++){
			MesThreads mt=new MesThreads();
			Tache t=new Tache();
			t.setDebut(debut);
			if(i==(nbrdethreads-1)){
				t.setFin(Singleton.getInstance().getLc().size()-1);
			}else{
				t.setFin(debut+blocsize);
			}
			
			t.setChampionID(id);
			//t.setBlocsize(blocsize);
			mt.setTache(t);
			mt.start();
			debut=debut+blocsize;
		}
		
		
		
	}
}
