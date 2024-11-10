package com.nali.small.mix.memo.server;

import com.nali.small.mix.memo.IBothN;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ServerN implements IBothN
{
	@Override
	public boolean doesSideBlockRendering(EnumFacing enumfacing)
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render()
	{
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateLight(World world, BlockPos blockpos)
	{
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void light()
	{
	}
}
