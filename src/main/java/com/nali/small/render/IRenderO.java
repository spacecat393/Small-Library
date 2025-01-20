package com.nali.small.render;

import com.nali.da.IBothDaO;
import com.nali.render.RenderO;
import com.nali.small.draw.Draw;
import com.nali.small.draw.DrawDa;
import com.nali.small.draw.DrawMap;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public interface IRenderO<BD extends IBothDaO, R extends RenderO<BD>>
{
	default void startDrawLater(BD bd, R r, DrawDa drawda)
	{
		for (int i = bd.O_StartPart(); i < bd.O_EndPart(); ++i)
		{
			this.drawLater(r, i);
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
		drawda.light_b = r.light_b;
		drawda.light_s = r.light_s;
	}

	default void endDrawLater(DrawDa drawda)
	{
		Draw.DRAWDA_LIST.add(drawda);
		++Draw.DATA_SIZE;
	}

	default void drawLater(R r, int index)
	{
		MemoG rg = G_LIST.get(index);
//		Draw.add(Draw.KEY_MAP.computeIfAbsent(rg + " " + r.getTextureBuffer(rg) + " " + r.getShaderID(rg) + " " + (byte)((r.getTransparent(rg) ? 1 : 0) | r.getExtraBit(rg)), k -> this.createByteArray(r, rg)));
		Draw.add(DrawMap.get(new DrawMap(index, r.getTextureBuffer(rg), r.getShaderID(rg), (byte)((r.getTransparent(rg) ? 1 : 0) | r.getExtraBit(rg)))));
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
