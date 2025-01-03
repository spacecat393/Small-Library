package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaBox;
import com.nali.small.render.SmallRenderO;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class RenderBox
<
	BD extends IBothDaO
> extends SmallRenderO<BD>
{
	public static Map<Integer, Integer> COLOR_MAP = new HashMap();//ebo hex
	public byte extra_bit;

	public static void setTextureMap()
	{
		COLOR_MAP.clear();
		COLOR_MAP.put((G_LIST.get(BothDaBox.IDA.O_StartPart() + 1)).ebo, 0xFFffc196);
		COLOR_MAP.put((G_LIST.get(BothDaBox.IDA.O_StartPart() + 2)).ebo, 0xFFffc196);
	}

	@Override
	public void setTextureUniform(MemoG rg, MemoS rs)
	{
		Integer integer = COLOR_MAP.get(rg.ebo);
		if (integer == null)
		{
			this.extra_bit = 0;
			super.setTextureUniform(rg, rs);
		}
		else
		{
			this.extra_bit = 4;
			int color = this.getTextureID(rg);
//			FLOATBUFFER.limit(3);
			FLOATBUFFER.clear();
			FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
			FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
			FLOATBUFFER.put((color & 0xFF) / 255.0F);
			FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
			FLOATBUFFER.flip();
			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4/*+1*/], FLOATBUFFER);
		}
	}

	@Override
	public int getTextureID(MemoG rg)
	{
		Integer integer = COLOR_MAP.get(rg.ebo);
		if (integer == null)
		{
			this.extra_bit = 0;
			return super.getTextureID(rg);
		}
		else
		{
			this.extra_bit = 4;
			return integer;
		}
	}

	@Override
	public void setLightMapUniform(MemoS rs)
	{
		if (this.extra_bit == 4)
		{
			OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5/*+1*/], 1);
//			OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//			setLightMapBuffer(((IMixinEntityRenderer) Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
		}
		else
		{
			super.setLightMapUniform(rs);
		}
	}

	@Override
	public byte getExtraBit(MemoG rg)
	{
		return this.extra_bit;
	}
}
