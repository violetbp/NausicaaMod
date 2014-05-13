package mooklabs.nausicaamod.blocks.machines;


public interface PowerEmitter extends IPowerBlockBase{
	
	
	/**Max it can output<br>
	 * no limit -> Integer.MAX_VALUE<br>
	 * should be between 0-100
	 * @return the max this can output
	 */
	public int maxOutput();
	
	/*
	 * ammount being outputed
	 * @return
	 *
	public int output(int x, int y, int z);*/
	/**
	 * ammount being outputed
	 * @return
	 */
	public int[] outputSide();
	
	
}
