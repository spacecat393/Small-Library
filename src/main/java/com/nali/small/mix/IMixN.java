package com.nali.small.mix;

import com.nali.small.mix.memo.IBothN;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMixN
<
	B extends IBothN,
	E
>
{
	default void Ninit()
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			this.newC();
		}
		else
		{
			this.newS();
		}
	}

	@SideOnly(Side.CLIENT)
	void render();
	@SideOnly(Side.CLIENT)
	void updateLight(World world, BlockPos blockpos);
	@SideOnly(Side.CLIENT)
	void light();

	@SideOnly(Side.CLIENT)
	void newC();

	void newS();

	B getB();
	E getE();
}
