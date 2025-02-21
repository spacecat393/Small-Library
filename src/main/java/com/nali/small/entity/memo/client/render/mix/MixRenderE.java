package com.nali.small.entity.memo.client.render.mix;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.system.Time;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MixRenderE
<
	BD extends IBothDaE & IBothDaO,
	R extends RenderO<BD>,
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, R, E, I, MB, ?, C>,
	MB extends MixBoxE<BD, R, E, I, MC, ?, C>,
	C extends ClientE<BD, R, E, I, MC, MB, ?>
>
{
	public C c;

	public float shadow_opaque, shadow_size;
	public double x, y, z;

	public MixRenderE(C c)
	{
		this.c = c;
	}

	public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
	{
		E e = this.c.i.getE();
		this.x += (e.posX - this.x) * Time.LINE;
		this.y += (e.posY - this.y) * Time.LINE;
		this.z += (e.posZ - this.z) * Time.LINE;
//		Nali.warn("Time.LINE " + Time.LINE);
//
//		Nali.warn("ox " + ox);
//		Nali.warn("oy " + oy);
//		Nali.warn("oz " + oz);
//
//		Nali.warn("e.posX " + e.posX);
//		Nali.warn("e.posY " + e.posY);
//		Nali.warn("e.posZ " + e.posZ);
//
//		Nali.warn("x " + this.x);
//		Nali.warn("y " + this.y);
//		Nali.warn("z " + this.z);
	}
}
