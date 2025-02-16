package com.nali.small.entity.memo.client.box.hit;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HitESit
<
	BD extends IBothDaE & IBothDaO & IBothDaNe,
	R extends RenderO<BD>,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	C extends ClientLe<BD, R, E, I, MC, MB, MR>
> extends HitE<BD, R, E, I, MC, MR, MB, C>
{
	public final static HitESit HITESIT = new HitESit(null);

	public HitESit(C c)
	{
		super(c);
	}

	@Override
	public void run(Entity player_entity)
	{
	}

	@Override
	public boolean should(Entity player_entity)
	{
		return false;
	}
}
