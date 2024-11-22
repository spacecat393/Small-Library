package com.nali.small.mix.memo.client;

import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothN;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ClientN
<
	BD extends IBothDaO,
	R extends RenderO<BD>,
	I extends IMixN<BD, ?, E>,
	E
> implements IBothN
{
//	public static List<ClientN> N_LIST = new ArrayList();
//	public byte face_byte;
	public byte state;//1-32 disable face

	public R r;
	public I i;

	public ClientN(R r, I i)
	{
//		N_LIST.add(this);
		this.r = r;
		this.i = i;
	}

	@Override
	public boolean doesSideBlockRendering(EnumFacing enumfacing)
	{
//		Nali.warn("value " + (1 << enumfacing.ordinal()));
//		this.face_byte |= 1 << enumfacing.ordinal();
//		Nali.warn("face_byte " + this.face_byte);
		byte ordinal = (byte)(1 << enumfacing.ordinal());
		return (this.state & ordinal) == ordinal;
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

		this.r.draw(this.i.getBD());

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
