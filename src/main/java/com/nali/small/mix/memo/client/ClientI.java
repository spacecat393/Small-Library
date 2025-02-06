package com.nali.small.mix.memo.client;

import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothI;
import com.nali.small.render.IRenderO;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientI
<
	BD extends IBothDaO,
	R extends RenderO<BD> & IRenderO<BD, R>,
	I extends IMixN<BD, ?, E>,
	E extends Item
> extends ClientN<BD, R, I, E> implements IBothI<E>
{
	public ClientI(R r, I i)
	{
		super(r, i);
	}
}
