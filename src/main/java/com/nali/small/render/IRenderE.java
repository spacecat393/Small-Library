package com.nali.small.render;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.draw.Draw;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IRenderE
<
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, ?, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, ?, E, I, MC, MR, C>,
	MR extends MixRenderE<BD, ?, E, I, MC, MB, C>,
	C extends ClientE<BD, ?, E, I, MC, MB, MR>,
	BD extends IBothDaO & IBothDaE,
	R extends RenderO<BD> & IRenderO<BD, R>
> extends IRenderO<BD, R>
{
	R getR();
	C getC();

	@Override
	default void updateLight(World world, BlockPos blockpos)
	{
//		Nali.warn("updateLight");
		I i = this.getC().i;
//		if (i != null)
//		{
		if (i.getE().isBurning())
		{
			Draw.LIGHT_B = 0.0F;
//			Draw.LIGHT_S = 0.0F;
			return;
		}
//		}

		IRenderO.super.updateLight(world, blockpos);
	}

	@Override
	default boolean getTransparent()
	{
//		Nali.warn("getTransparent");
		I i = this.getC().i;
		boolean result = IRenderO.super.getTransparent();
		if (i == null)
		{
			return result;
		}

		E e = i.getE();
		return result || e == null || e.isInvisible();
	}

	default void drawE()
	{
//		Nali.warn("draw");
		R r = this.getR();
		float light_b = Draw.LIGHT_B;
		float light_s = Draw.LIGHT_S;

		if (r.getGlow())
		{
			Draw.LIGHT_B = 0.0F;
//			Draw.LIGHT_S = 0.0F;
		}

		r.draw();

		Draw.LIGHT_B = light_b;
		Draw.LIGHT_S = light_s;
	}

	default void setUniformE()
	{
//		Nali.warn("setUniform");
		R r = this.getR();
		r.setUniform();
		r.setMapUniform();
		r.setLightUniform();
	}
}
