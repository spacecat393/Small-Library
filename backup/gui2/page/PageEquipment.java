package com.nali.small.gui.page;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class PageEquipment extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static byte BYTE = 1;
//	public static ItemStack[] ITEMSTACK_ARRAY;

	@Override
	public void init()
	{
		if ((BYTE & 1) == 1)
		{
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

			//s0-initEquipment
			//e0-initEquipment

			BYTE &= 255-1;
		}
	}

	@Override
	public void detect()
	{

	}

	@Override
	public void preDraw()
	{

	}

	@Override
	public void draw()
	{

	}
}
