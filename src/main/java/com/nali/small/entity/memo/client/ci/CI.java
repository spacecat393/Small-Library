package com.nali.small.entity.memo.client.ci;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;

//@SideOnly(Side.CLIENT)
public abstract class CI
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, C>,
	C extends ClientE<BD, R, E, I, MC, MB, MR>
>
{
	public C c;

	public CI(C c)
	{
		this.c = c;
	}

//	public abstract void init();
	public abstract void call();
	public abstract void onUpdate();
//	public abstract void onReadNBT();
}
