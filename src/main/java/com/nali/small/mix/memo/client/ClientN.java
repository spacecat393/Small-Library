package com.nali.small.mix.memo.client;

import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothN;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ClientN<RC extends IClientDaO, R extends RenderO<RC>, I extends IMixN<?, E>, E> implements IBothN
{
	public R r;
	public I i;

	public ClientN(R r, I i)
	{
		this.r = r;
		this.i = i;
	}

	@Override
	public void render()
	{
//		this.d.render();
		GL11.glPushMatrix();

//		GL11.glTranslatef(0.0F, 0.0F, 50.0F);
//		GL11.glScalef(-25.0F, -25.0F, -25.0F);
		GL11.glScalef(0.25F, 0.25F, 0.25F);
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
//		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);

		this.r.draw();

		GL11.glPopMatrix();
	}

	@Override
	public void updateLight(World world, BlockPos blockpos)
	{
		this.r.lig_b = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
		this.r.lig_s = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;

		if (this.r.lig_b < 0.1875F)
		{
			this.r.lig_b = 0.1875F;
		}
	}

	@Override
	public void light()
	{
		this.r.lig_b = -1.0F;
	}
}
