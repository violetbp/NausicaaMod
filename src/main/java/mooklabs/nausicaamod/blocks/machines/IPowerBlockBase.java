package mooklabs.nausicaamod.blocks.machines;


interface IPowerBlockBase {
	public final byte TOP = 0;
	public final byte BOTTOM = 1;
	public final byte NORTH = 2;
	public final byte SOUTH = 3;
	public final byte EAST = 4;
	public final byte WEST = 5;

	public final int[] ALL_SIDES = {EAST, WEST, NORTH, SOUTH, TOP, BOTTOM };
	
}
