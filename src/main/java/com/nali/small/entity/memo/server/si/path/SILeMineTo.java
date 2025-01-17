package com.nali.small.entity.memo.server.si.path;

import com.nali.da.IBothDaE;
import com.nali.list.entity.si.SIEMineTo;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.item.ItemStack;

public class SILeMineTo
<
	BD extends IBothDaE,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SIEMineTo<BD, E, I, S, MS>
{
	public SILeMineTo(S s)
	{
		super(s);
	}

	@Override
	public ItemStack getItemStack(E e)
	{
		return e.getHeldItemMainhand();
	}

	public void damageItem(E e)
	{
		this.getItemStack(e).damageItem(1, e);
	}
}
