package mooklabs.nausicaamod.tea;



public enum Teas {
	
	public static HashMap <int, Teas> teaMap = new HashMap();
	static {
		teaMap.add();
	}
	
	Black("Black","Black tea is __ and etc",10),Chamomile("Chamomile","",10),Green("Green","",10), Mate("Mate","",10), Mint("Mint","",10), White("White","",10);
	
	String name;
	String desc;
	int cookTime;
	
	
	 private Teas(String nam, String description, int cooktime){
	 	name = nam;
	 	desc = description;
		cookTime = cooktime;
		
	}
}
