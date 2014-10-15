package mooklabs.nausicaamod.tea;

import java.util.HashMap;

public enum Teas {	
	
	Black("Black","Black tea is yummy etc",10), Chamomile("Chamomile","good with honey for sick people",10), Green("Green","japanese",10), Matte("Matte","isnt this basically green tea",10), Mint("Peppermint","good for when you're sick!",10), White("White","what are you, racist",10);
	
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
				//teaMap.put(x++, t);
			}
		}
		
	public String toString(){
			
		return name;
	}
}
