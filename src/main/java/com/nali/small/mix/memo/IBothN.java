package com.nali.small.mix.memo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBothN
{
	@SideOnly(Side.CLIENT)
	boolean doesSideBlockRendering(EnumFacing enumfacing);
	@SideOnly(Side.CLIENT)
	void render();
	@SideOnly(Side.CLIENT)
	void updateLight(World world, BlockPos blockpos);
	@SideOnly(Side.CLIENT)
	void light();
}
