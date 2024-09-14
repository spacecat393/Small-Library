package com.nali.small.entity.memo.client.box.hit;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class HitE
<
	RC extends IClientDaO,
	R extends RenderO<RC>,
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<RC, R, BD, E, I, MB, MR, C>,
	MR extends MixRenderE<RC, R, BD, E, I, MC, MB, C>,
	MB extends MixBoxE<RC, R, BD, E, I, MC, MR, C>,
	C extends ClientE<RC, R, BD, E, I, MC, MB, MR>
>
{
	public C c;

	public HitE(C c)
	{
		this.c = c;
	}

	public abstract void run(Entity player_entity, AxisAlignedBB axisalignedbb);
	public abstract boolean should(Entity player_entity, AxisAlignedBB axisalignedbb);
}
