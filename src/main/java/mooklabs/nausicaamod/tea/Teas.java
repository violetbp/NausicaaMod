package mooklabs.nausicaamod.tea;

import java.util.HashMap;



public enum Teas {	
	
	Black("Black","Black tea is __ and etc",10), Chamomile("Chamomile","",10), Green("Green","",10), Mate("Mate","",10), Mint("Mint","",10), White("White","",10);
	
	String name;
	String desc;
	int cookTime;
	
	 private Teas(String nam, String description, int cooktime){
	 	name = nam;
	 	desc = description;
		cookTime = cooktime;
		
	}
	 
	 
	 public static HashMap <Integer, Teas> teaMap;
		static {
			int x = 0;
			for(Teas t: Teas.values()){
				teaMap.put(x++, t);
			}
		}
		
	public String toString(){
			
		return name;
	}
}
