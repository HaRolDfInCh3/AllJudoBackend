package com.example.ApiDoublons.services;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class MesThreads extends Thread {
	Tache tache;
	@Override
	public void run() {
		System.out.println("Debut thread: "+this.getName()+". De "+this.tache.debut+" a "+this.tache.fin);
		tache.demarre();
		Singleton.getInstance().inc();
		int reste=Search.nbrdethreads-Singleton.getInstance().getFinis();
		System.out.println("thread: "+this.getName()+" a fini. Il reste "+reste);
		super.run();
	}
}
