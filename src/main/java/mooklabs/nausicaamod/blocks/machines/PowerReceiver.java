package mooklabs.nausicaamod.blocks.machines;


public interface PowerReceiver extends IPowerBlockBase{
	
	/**Max it can take as input before spinning out<br>
	 * no limit -> Integer.MAX_VALUE<br>
	 * MUST be between 0-100
	 * @return the max this can take as input
	 */
	public int maxNeededInput();
	
	/*
	 * ammount being inputed
	 * @return
	 *
	public int input(int x, int y, int z);*/
	
	
	
	/**
	 * side to look for input
	 * @return
	 */
	public int[] inputSide();
	
	
	
	
	
}
