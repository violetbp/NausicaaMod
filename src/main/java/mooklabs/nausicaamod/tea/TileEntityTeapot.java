package mooklabs.nausicaamod.tea;

import net.minecraft.tileentity.TileEntity;


public class TileEntityTeapot extends TileEntity {

	public Teas teatype;
	
	public TileEntityTeapot() {
		teatype = Teas.Black;
	}

}
