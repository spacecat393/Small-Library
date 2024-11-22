package com.nali.small.entity.memo.client.render.layer;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerE
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	C extends ClientE<BD, R, E, I, MC, MB, MR>
>
{
	public C c;
	public LayerE(C c)
	{
		this.c = c;
	}
}
