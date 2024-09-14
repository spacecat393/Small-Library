package com.nali.list.block.tile.render;

import com.nali.list.block.tile.SmallTPBase;
import com.nali.small.tile.RenderT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SmallRenderTPBase
<
	T extends SmallTPBase
> extends RenderT<T>
{
}
