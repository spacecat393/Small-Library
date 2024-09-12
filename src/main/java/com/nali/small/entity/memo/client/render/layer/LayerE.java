package com.nali.small.entity.memo.client.render.layer;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
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
public class LayerE<RC extends IClientDaO, R extends RenderO<RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MC extends MixCIE<RC, R, SD, BD, E, I, MB, MR, C>, MR extends MixRenderE<RC, R, SD, BD, E, I, MC, MB, C>, MB extends MixBoxE<RC, R, SD, BD, E, I, MC, MR, C>, C extends ClientE<RC, R, SD, BD, E, I, MC, MB, MR>>
{
	public C c;
	public LayerE(C c)
	{
		this.c = c;
	}
}
