package com.nali.list.render.o;

import com.nali.da.client.IClientDaO;
import com.nali.small.da.client.BoxClient;
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
	RC extends IClientDaO
> extends SmallRenderO<RC>
{
//	public static int ID;
//	public static DataLoader DATALOADER = RenderHelper.DATALOADER;
	public static IClientDaO ICLIENTDAO = new BoxClient();
	public Map<Integer, Integer> color_map = new HashMap();//ebo hex
	public byte extra_bit;

	public RenderBox(RC rc)
	{
		super(rc);
		color_map.put((G_LIST.get(ICLIENTDAO.StartPart() + 1)).ebo, 0xFFffc196);
		color_map.put((G_LIST.get(ICLIENTDAO.StartPart() + 2)).ebo, 0xFFffc196);
	}

	@Override
	public void setTextureUniform(MemoG rg, MemoS rs)
	{
		Integer integer = this.color_map.get(rg.ebo);
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
		Integer integer = this.color_map.get(rg.ebo);
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
