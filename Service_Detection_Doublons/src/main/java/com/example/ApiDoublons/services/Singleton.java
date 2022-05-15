package com.example.ApiDoublons.services;

import java.util.ArrayList;
import java.util.List;

import com.example.ApiDoublons.entities.Champion;
import com.example.ApiDoublons.repositories.ChampionRepository;

import lombok.*;

@AllArgsConstructor  @Data
public class Singleton {
	private static Singleton instance;
   private ChampionRepository cr;
   private Champion champion;
    private List<Champion>lc;
    private List<Integer>pos=new ArrayList<Integer>();
    private List<Champion>resultats=new ArrayList<Champion>();
    private List<List<Champion>>resultats2=new ArrayList<List<Champion>>();
    private int finis=0;
    public boolean parlots;
    private Singleton() {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
    }
    public void clear(){
    	resultats.clear();
    	finis=0;
    	champion=null;
    }
    public void clear2(int i){
    	
    	finis=0;
    	champion=null;
    }
    public void inc(){
    	finis++;
    	if(finis==Search.nbrdethreads){
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		//System.out.println("Total: "+resultats.size());
    		//System.out.println(resultats);
    		
    	}
    }
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
