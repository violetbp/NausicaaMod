package mooklabs.nausicaamod.tea;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemLeaves;

/**
 * leaves
 * @author emilynewman
 *
 */
public class TeaLeaves extends ItemLeaves{
	private final BlockLeaves field_150940_b;
	public TeaLeaves(BlockLeaves p_i45344_1_)
    {
        super(p_i45344_1_);
        this.field_150940_b = p_i45344_1_;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
}
