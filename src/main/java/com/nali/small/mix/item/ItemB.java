package com.nali.small.mix.item;

import com.nali.da.IBothDaO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothB;
import com.nali.small.mix.memo.IBothN;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemB extends ItemBlock implements IMixN
{
	public IMixN imixn;

	public ItemB(IMixN<IBothDaO, IBothB<TileEntity, Block>, Block> imixn)
	{
		super(imixn.getE());
		this.Ninit();
		this.imixn = imixn;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render()
	{
		this.imixn.render();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateLight(World world, BlockPos blockpos)
	{
		this.imixn.updateLight(world, blockpos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void light()
	{
		this.imixn.light();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void newC()
	{
	}

	@Override
	public void newS()
	{
	}

	@Override
	public IBothDaO getBD()
	{
		return this.imixn.getBD();
	}

	@Override
	public IBothN getB()
	{
		return this.imixn.getB();
	}

//	@Override
//	public IBothI getB()
//	{
//		return null;
//	}

	@Override
	public Item getE()
	{
		return this;
	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	public ObjectRender getObjectRender()
//	{
//		return ((BlockB)this.block).getObjectRender();
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public DrawScreen getDrawScreen()
//	{
//		return ((BlockB)this.block).getDrawScreen();
//	}
}
