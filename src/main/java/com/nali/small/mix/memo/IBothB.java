package com.nali.small.mix.memo;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBothB<T extends TileEntity, E extends Block> extends IBothN
{
	default void init(E e, String name, String mod_id, CreativeTabs creativetabs)
	{
		e.setRegistryName(mod_id, name);
		e.setCreativeTab(creativetabs);
		e.setTranslationKey(mod_id + '.' + name);
	}

	@SideOnly(Side.CLIENT)
	void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha);
}
