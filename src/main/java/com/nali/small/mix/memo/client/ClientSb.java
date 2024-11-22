package com.nali.small.mix.memo.client;

import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.mix.IMixN;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class ClientSb
<
	BD extends IBothDaO & IBothDaS,
	R extends RenderS<BD>,
	I extends IMixN<BD, ?, E>,
	E extends Block,
	T extends TileEntity
> extends ClientB<BD, R, I, E, T>
{
	public ClientSb(R r, I i)
	{
		super(r, i);
	}

	@Override
	public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		BD bd = this.i.getBD();

		this.r.updateLightCoord(t.getWorld(), t.getPos().up());
		this.r.initSkinning(bd/*memoanimation*/);
		this.updateFrame(this.r);
		this.r.setSkinning(bd/*memoanimation*/);

		super.render(t, x, y, z, partialTicks, destroyStage, alpha);
//		GL11.glPushMatrix();
//		this.translate(x, y, z);
//		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//		this.scale();
////		MemoAnimation memoanimation = this.r.rst.memoanimation_list.get(this.r.rc.AnimationID());
//
//		this.r.drawLater();
////		this.r.draw();
//		GL11.glPopMatrix();
	}

	@Override
	public void translate(double x, double y, double z)
	{
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);//0.5
	}

	@Override
	public void scale()
	{
		GL11.glScalef(0.5F, 0.5F, 0.5F);
	}

	public abstract void updateFrame(R r);
}
