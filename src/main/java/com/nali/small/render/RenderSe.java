package com.nali.small.render;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSe
<
	E extends Entity,
	I extends IMixE<BD, E> & IMixES,
	MC extends MixCIE<BD, ?, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, ?, E, I, MC, MR, C>,
	MR extends MixRenderSe<BD, ?, E, I, MC, MB, C>,
	C extends ClientE<BD, ?, E, I, MC, MB, MR>,
	BD extends IBothDaO & IBothDaS & IBothDaE
> extends RenderS<BD>
{
	public C c;

	public RenderSe(BD bd)
	{
		super(bd);
//		this.c = c;
	}

	public void updateLightCoord()
	{
		E e = this.c.i.getE();
		if (e.isBurning())
		{
			this.lig_b = -1.0F;
			this.lig_s = -1.0F;
			return;
		}

		this.updateLightCoord(e.world, e.getPosition());
	}

	@Override
	public boolean getTransparent(MemoG rg)
	{
		boolean result = super.getTransparent(rg);
		I i = this.c.i;
		if (i == null)
		{
			return result;
		}

		E e = i.getE();
		return result/* || e == null*/ || e.isInvisible() || e.isInvisibleToPlayer(Minecraft.getMinecraft().player);
	}

	@Override
	public byte getExtraBit(MemoG rg)
	{
		return (byte)(super.getExtraBit(rg) | (this.c.i.getE().isGlowing() ? 8 : 0));
	}
}
