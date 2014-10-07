package mooklabs.nausicaamod.tea;


public enum Teas {
	Black(10),Chamomile(10),Green(10), Mate(10), Mint(10), White(10);
	
	int cookTime;
	
	 private Teas(int cooktime){
		 cookTime = cooktime;
		 
	}
}
