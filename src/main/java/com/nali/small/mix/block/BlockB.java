package com.nali.small.mix.block;

import com.nali.small.SmallTab;
import com.nali.small.mix.IMixB;
import com.nali.small.mix.memo.IBothB;
import com.nali.small.mix.memo.IBothN;
import com.nali.small.mix.memo.server.ServerB;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockB extends Block implements IMixB
{
	public IBothB ibothb;

	public BlockB(String[] string_array, Material material)
	{
		super(material);
		this.Ninit();
		this.ibothb.init(this, string_array[0], string_array[1], SmallTab.TAB);
	}

	@Override
	public void render(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		this.ibothb.render(tileEntity, x, y, z, partialTicks, destroyStage, alpha);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render()
	{
		this.ibothb.render();
	}

	@Override
	public void updateLight(World world, BlockPos blockpos)
	{
		this.ibothb.updateLight(world, blockpos);
	}

	@Override
	public void light()
	{
		this.ibothb.light();
	}

	@Override
	public void newS()
	{
		this.ibothb = new ServerB();
	}

	@Override
	public IBothN getB()
	{
		return this.ibothb;
	}
}
