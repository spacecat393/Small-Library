package com.nali.small.render;

import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.draw.Draw;
import com.nali.small.draw.DrawDa;
import com.nali.small.draw.DrawMap;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.system.ClientLoader.G_LIST;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public interface IRenderO
<
	BD extends IBothDaO,
	R extends RenderO<BD>
>
{
	R getR();

	default void startDrawLater(DrawDa drawda)
	{
		R r = this.getR();
		for (r.i = r.bd.O_StartPart(); r.i < r.bd.O_EndPart(); ++r.i)
		{
			r.rg = G_LIST.get(r.i);
			r.rs = S_LIST.get(r.getShaderID());
			this.drawLater();
		}

		RenderO.FLOATBUFFER.limit(16);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, RenderO.FLOATBUFFER);
		Draw.add(drawda.projection_m4x4_float, RenderO.FLOATBUFFER);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, RenderO.FLOATBUFFER);
		Draw.add(drawda.modelview_m4x4_float, RenderO.FLOATBUFFER);
		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, RenderO.FLOATBUFFER);
		RenderO.FLOATBUFFER.limit(4);
		Draw.add(drawda.color_v4_float, RenderO.FLOATBUFFER);
		GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_POSITION, RenderO.FLOATBUFFER);
		Draw.add(drawda.light0position_v4_float, RenderO.FLOATBUFFER);
//		drawda.light_b = r.light_b;
//		drawda.light_s = r.light_s;
		drawda.light_b = Draw.LIGHT_B;
		drawda.light_s = Draw.LIGHT_S;
	}

	//clean
	default void endDrawLater(DrawDa drawda)
	{
		Draw.DRAWDA_LIST.add(drawda);
		++Draw.DATA_SIZE;
	}

	default void drawLater()
	{
		R r = this.getR();
		Draw.add(DrawMap.get(new DrawMap(r.i, r.getTextureBuffer(), r.getShaderID(), this.getExtraBit())));
	}

	default byte getExtraBit()
	{
		return (byte)(this.getTransparent() ? DrawMap.B_TRANSPARENT : 0);
	}

	default void updateLight(World world, BlockPos blockpos)
	{
//		if (world.isBlockLoaded(blockpos))
//		{
//			int brightness = world.getCombinedLight(blockpos, 0);
//			this.light_b = (brightness % 65536) / 255.0F;
//			this.light_s = (brightness / 65536.0F) / 255.0F;
//		}
//
//		if (this.light_b < 0.1875F)
//		{
//			this.light_b = 0.1875F;
//		}

//		this.light_b = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;//0-15
//		this.light_s = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;
		Draw.LIGHT_B = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
		Draw.LIGHT_S = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;

//		if (this.light_b < 0.0625F)//1/16
//		{
//			this.light_b = 0.0625F;
//		}
//
//		if (this.light_s < 0.0625F)//1/16
//		{
//			this.light_s = 0.0625F;
//		}
		if (Draw.LIGHT_B < 0.0625F)//1/16
		{
			Draw.LIGHT_B = 0.0625F;
		}

		if (Draw.LIGHT_S < 0.0625F)//1/16
		{
			Draw.LIGHT_S = 0.0625F;
		}
	}

	default void setMapUniform()
	{
//		if (this.light_b != -1.0F)
		if (Draw.LIGHT_B != -1.0F)
		{
			OpenGlHelper.glUniform1i(this.getR().rs.uniformlocation_int_array[2], 1);
		}
	}

	default void setLightUniform()
	{
		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(this.light_b);
//		RenderO.FLOATBUFFER.put(this.light_s);
		RenderO.FLOATBUFFER.put(Draw.LIGHT_B);
		RenderO.FLOATBUFFER.put(Draw.LIGHT_S);
		RenderO.FLOATBUFFER.flip();
		OpenGlHelper.glUniform2(this.getR().rs.uniformlocation_int_array[3], RenderO.FLOATBUFFER);
	}

//	boolean getGlow();
	default boolean getGlow()
	{
		return (this.getR().rg.state & MemoG.B_GLOW) == MemoG.B_GLOW;
	}

//	boolean getTransparent();
	default boolean getTransparent()
	{
		return (this.getR().rg.state & MemoG.B_TRANSPARENT) == MemoG.B_TRANSPARENT;
	}

//	default byte[] createByteArray(R r, int rg)
//	{
//		MemoG rg = G_LIST.get(rg);
//		byte[] byte_array = new byte[4 + 4 + 4 + 1];
//		ByteWriter.set(byte_array, rg, 0);
//		ByteWriter.set(byte_array, r.getTextureBuffer(rg), 4);
//		ByteWriter.set(byte_array, r.getShaderID(rg), 4 + 4);
//		byte_array[4 + 4 + 4] = (byte)(r.getTransparent(rg) ? 1 : 0);
//		byte_array[4 + 4 + 4] += r.getExtraBit(rg);
//		return byte_array;
//	}
}
