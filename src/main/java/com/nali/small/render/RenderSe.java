package com.nali.small.render;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
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
public class RenderSe
<
	E extends Entity,
	I extends IMixE<BD, E>,
	MC extends MixCIE<BD, ?, E, I, MB, MR, C>,
	MB extends MixBoxE<BD, ?, E, I, MC, MR, C>,
	MR extends MixRenderE<BD, ?, E, I, MC, MB, C>,
	C extends ClientE<BD, ?, E, I, MC, MB, MR>,
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD> & IRenderS<BD, R>
> extends RenderS<BD> implements IRenderS<BD, R>, IRenderE<E, I, MC, MB, MR, C, BD, R>
{
	public C c;

	public RenderSe(BD bd)
	{
		super(bd);
	}

	@Override
	public C getC()
	{
		return this.c;
	}

	@Override
	public R getR()
	{
		return (R)this;
	}

	@Override
	public void updateLight(World world, BlockPos blockpos)
	{
		IRenderE.super.updateLight(world, blockpos);
	}

	@Override
	public boolean getTransparent()
	{
		return IRenderE.super.getTransparent();
	}

	@Override
	public void draw()
	{
		this.drawE();
//		IRenderE.super.draw();
//		super.draw();
	}

	@Override
	public void setUniform()
	{
		this.setUniformE();
//		IRenderE.super.setUniform();
//		super.setUniform();
	}
}
