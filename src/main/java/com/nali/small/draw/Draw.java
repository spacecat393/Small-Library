package com.nali.small.draw;

import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.render.RenderO;
import com.nali.render.RenderS;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class Draw
{
	public static List<DrawDa> DRAWDA_LIST = new ArrayList();
	public static Map<Integer, float[]> KEY_FLOAT_ARRAY_LIST = new HashMap();
//	public static Map<Integer, double[]> FAR_DOUBLE_ARRAY_LIST = new HashMap();
	public static int DATA_SIZE;

	public static Map<DrawMap, List<Integer>> E_MODEL_MAP = new HashMap();
	public static Map<DrawMap, List<Integer>> E_TRANSLUCENT_MAP = new HashMap();

	public static Map<DrawMap, List<Integer>> TE_MODEL_MAP = new HashMap();
	public static Map<DrawMap, List<Integer>> TE_TRANSLUCENT_MAP = new HashMap();
//	public static List<DrawDa> TRANSLUCENT_DRAWDA_LIST = new ArrayList();

	public static float
		LIGHT_B,
		LIGHT_S;

	public static void add(DrawMap drawmap)
	{
		List<Integer> index_integer_list;
		Map<DrawMap, List<Integer>> drawmap_map;

		if ((drawmap.extra_bit & DrawMap.B_TILE_ENTITY) == 0)
		{
			if ((drawmap.extra_bit & DrawMap.B_TRANSPARENT) == 0)
			{
				index_integer_list = E_MODEL_MAP.get(drawmap);
				drawmap_map = E_MODEL_MAP;
			}
			else
			{
				index_integer_list = E_TRANSLUCENT_MAP.get(drawmap);
				drawmap_map = E_TRANSLUCENT_MAP;
			}
		}
		else
		{
			if ((drawmap.extra_bit & DrawMap.B_TRANSPARENT) == 0)
			{
				index_integer_list = TE_MODEL_MAP.get(drawmap);
				drawmap_map = TE_MODEL_MAP;
			}
			else
			{
				index_integer_list = TE_TRANSLUCENT_MAP.get(drawmap);
				drawmap_map = TE_TRANSLUCENT_MAP;
			}
		}

		if (index_integer_list == null)
		{
			List list = new ArrayList();
			list.add(DATA_SIZE);
			drawmap_map.put(drawmap, list);
		}
		else
		{
			index_integer_list.add(DATA_SIZE);
		}
	}

	public static void add(float[] float_array, FloatBuffer floatbuffer)
	{
		for (int i = 0; i < floatbuffer.limit(); ++i)
		{
			float_array[i] = floatbuffer.get(i);
		}
	}

//	public static void run()
//	{
//		RenderO.take();
//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//
//		if (!E_MODEL_MAP.isEmpty())
//		{
//			draw(E_MODEL_MAP);
//		}
//
//		//model texture
////		if (!E_GLOW_MAP.isEmpty())
////		{
////			draw(E_GLOW_MAP);
////		}
//
//		if (!E_TRANSLUCENT_MAP.isEmpty())
//		{
//			draw(E_TRANSLUCENT_MAP);
////			drawT();
//		}
//
//		RenderO.free();
//		Draw.clear();
//	}

//	public static void runE()
//	{
//		RenderO.take();
//
//		if (!E_MODEL_MAP.isEmpty())
//		{
//			draw(E_MODEL_MAP);
//		}
//
//		//model texture
////		if (!E_GLOW_MAP.isEmpty())
////		{
////			draw(E_GLOW_MAP);
////		}
//
//		if (!E_TRANSLUCENT_MAP.isEmpty())
//		{
//			GL11.glDepthMask(false);
//			draw(E_TRANSLUCENT_MAP);
//		}
//
//		RenderO.free();
//	}

//	public static void runEG()
//	{
//		//outline
//		if (!E_GLOW_MAP.isEmpty())
//		{
//			RenderO.take();
//			draw(E_GLOW_MAP);
//			RenderO.free();
//		}
//	}

//	public static void runT()
//	{
//		RenderO.take();
//
//		if (!T_MODEL_MAP.isEmpty())
//		{
//			draw(T_MODEL_MAP);
//		}
//
//		if (!T_TRANSLUCENT_MAP.isEmpty())
//		{
//			GL11.glDepthMask(false);
//			draw(T_TRANSLUCENT_MAP);
//		}
//
//		RenderO.free();
//	}

	public static void clear()
	{
		DATA_SIZE = 0;
		E_MODEL_MAP.clear();
		E_TRANSLUCENT_MAP.clear();
//		TRANSLUCENT_DRAWDA_LIST.clear();
//		E_MODEL_MAP.clear();
//		E_TRANSLUCENT_MAP.clear();
//		E_GLOW_MAP.clear();
//		T_MODEL_MAP.clear();
//		T_TRANSLUCENT_MAP.clear();
		DRAWDA_LIST.clear();
		KEY_FLOAT_ARRAY_LIST.clear();
		DrawMap.DRAWMAP_LIST.clear();

		TE_MODEL_MAP.clear();
		TE_TRANSLUCENT_MAP.clear();
	}

	public static void draw(Map<DrawMap, List<Integer>> model_map)
	{
		DrawMap[] drawmap_array = model_map.keySet().toArray(new DrawMap[0]);
		List<Integer>[] values_integer_list = model_map.values().toArray(new ArrayList[0]);

		for (int g = 0; g < drawmap_array.length; ++g)
		{
			DrawMap drawmap = drawmap_array[g];
			List<Integer> index_integer_list = values_integer_list[g];
//			Nali.warn("rg " + ByteReader.getInt(drawmap, 0));
			MemoG rg = G_LIST.get(drawmap.rg);
			MemoS rs = S_LIST.get(drawmap.rs);

			startBuffer(drawmap, rg, rs);
//			RenderO.enableBuffer(rg, rs);
//
//			OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 1);
//			if ((drawmap.extra_bit & 4) == 4)//color
//			{
//				int color = drawmap.texture;
//				RenderO.FLOATBUFFER.clear();
//				RenderO.FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.put((color & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.flip();
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[5], RenderO.FLOATBUFFER);
//			}
//			else
//			{
////				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////				Nali.warn("texture " + ByteReader.getInt(drawmap, 4));
////				Nali.warn("texture_state " + (rg.state & 1));
////				RenderO.setTextureBuffer(drawmap.texture, (byte)(rg.flag & 1+2));
//				GL11.glBindTexture(GL11.GL_TEXTURE_2D, drawmap.texture);
//			}

			for (Integer integer : index_integer_list)
			{
				render(integer, drawmap, rg, rs);
//				DrawDa drawda = DRAWDA_LIST.get(integer);
////				Nali.warn("g" + g + " i" + integer);
//
//				RenderO.FLOATBUFFER.limit(16);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.projection_m4x4_float, 16);
//				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, RenderO.FLOATBUFFER);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.modelview_m4x4_float, 16);
//				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, RenderO.FLOATBUFFER);
//				RenderO.FLOATBUFFER.limit(4);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.color_v4_float, 4);
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], RenderO.FLOATBUFFER);
////				MemoA.put(RenderO.FLOATBUFFER, drawda.light0position_v4_float, 4);
////				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], RenderO.FLOATBUFFER);
//
//				RenderO.FLOATBUFFER.clear();
//				if ((rg.flag & 16) == 16)
//				{
//					RenderO.FLOATBUFFER.put(0.0F);
//					RenderO.FLOATBUFFER.put(0.0F);
//				}
//				else
//				{
//					RenderO.FLOATBUFFER.put(drawda.light_b);
//					RenderO.FLOATBUFFER.put(drawda.light_s);
//				}
//				RenderO.FLOATBUFFER.flip();
//				OpenGlHelper.glUniform2(rs.uniformlocation_int_array[3], RenderO.FLOATBUFFER);
//
//				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, drawda.light_b, drawda.light_s);
//
//				if ((drawmap.extra_bit & 2) == 2)
//				{
//					RenderS.setFloatBuffer(KEY_FLOAT_ARRAY_LIST.get(integer));
//					OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[6], false, RenderS.BONE_FLOATBUFFER);
//				}
//
//				if (NaliConfig.VAO)
//				{
//					NaliGL.glDrawElementsTUi0(rg.index_length);
//				}
//				else
//				{
//					GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
//				}
			}

			endBuffer(rs);
//			if (!NaliConfig.VAO)
//			{
//				RenderO.disableBuffer(rs);
//			}
		}
	}

	public static void drawT(Map<DrawMap, List<Integer>> model_map)
	{
		DrawMap[] drawmap_array = model_map.keySet().toArray(new DrawMap[0]);
		List<Integer>[] values_integer_list = model_map.values().toArray(new ArrayList[0]);

		//s-sort
		List<Double> double_list = new ArrayList();
		List<Integer> drawmap_integer_list = new ArrayList();
		List<Integer> drawda_integer_list = new ArrayList();

		for (int g = 0; g < drawmap_array.length; ++g)
		{
//			DrawMap drawmap = drawmap_array[g];
			List<Integer> index_integer_list = values_integer_list[g];
			for (Integer integer : index_integer_list)
			{
				DrawDa drawda = DRAWDA_LIST.get(integer);
//				double[] far_double_array = FAR_DOUBLE_ARRAY_LIST.get();
//				double x = drawda.x + far_double_array[0];
//				double y = drawda.y + far_double_array[1];
//				double z = drawda.z + far_double_array[2];
//				double d = Minecraft.getMinecraft().player.getDistanceSq(x, y, z);
				double x = drawda.modelview_m4x4_float[12];
				double y = drawda.modelview_m4x4_float[13];
				double z = drawda.modelview_m4x4_float[14];
				double d = x * x + y * y + z * z;
				double_list.add(-d);

				drawda_integer_list.add(integer);
				drawmap_integer_list.add(g);
			}
		}

		int double_list_size = double_list.size();

		double_list_size -= 1;
		for (int x = 0; x < double_list_size; ++x)
		{
			for (int y = 0; y < double_list_size; ++y)
			{
				int y1 = y + 1;
				double y_far = double_list.get(y);
				double y1_far = double_list.get(y1);
				if (y_far > y1_far)
				{
					double_list.set(y, y1_far);
					double_list.set(y1, y_far);

					Integer y_drawda_integer = drawda_integer_list.get(y);
					drawda_integer_list.set(y, drawda_integer_list.get(y1));
					drawda_integer_list.set(y1, y_drawda_integer);

					Integer y_drawmap_integer = drawmap_integer_list.get(y);
					drawmap_integer_list.set(y, drawmap_integer_list.get(y1));
					drawmap_integer_list.set(y1, y_drawmap_integer);
				}
			}
		}
 		//e-sort

		for (int g = 0; g < drawmap_integer_list.size(); ++g)
		{
			DrawMap drawmap = drawmap_array[drawmap_integer_list.get(g)];
//			List<Integer> index_integer_list = values_integer_list[g];
//			Nali.warn("rg " + ByteReader.getInt(drawmap, 0));
			MemoG rg = G_LIST.get(drawmap.rg);
			MemoS rs = S_LIST.get(drawmap.rs);

			startBuffer(drawmap, rg, rs);
//			RenderO.enableBuffer(rg, rs);
//
//			OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 1);
//			if ((drawmap.extra_bit & 4) == 4)//color
//			{
//				int color = drawmap.texture;
//				RenderO.FLOATBUFFER.clear();
//				RenderO.FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.put((color & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
//				RenderO.FLOATBUFFER.flip();
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[5], RenderO.FLOATBUFFER);
//			}
//			else
//			{
////				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////				Nali.warn("texture " + ByteReader.getInt(drawmap, 4));
////				Nali.warn("texture_state " + (rg.state & 1));
////				RenderO.setTextureBuffer(drawmap.texture, (byte)(rg.flag & 1+2));
//				GL11.glBindTexture(GL11.GL_TEXTURE_2D, drawmap.texture);
//			}

			render(drawda_integer_list.get(g), drawmap, rg, rs);
////			for (Integer integer : drawda_integer_list)
//			{
//				Integer integer = drawda_integer_list.get(g);
//				DrawDa drawda = DRAWDA_LIST.get(integer);
////				Nali.warn("g" + g + " i" + integer);
//
//				RenderO.FLOATBUFFER.limit(16);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.projection_m4x4_float, 16);
//				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, RenderO.FLOATBUFFER);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.modelview_m4x4_float, 16);
//				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, RenderO.FLOATBUFFER);
//				RenderO.FLOATBUFFER.limit(4);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.color_v4_float, 4);
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], RenderO.FLOATBUFFER);
////				MemoA.put(RenderO.FLOATBUFFER, drawda.light0position_v4_float, 4);
////				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], RenderO.FLOATBUFFER);
//
//				RenderO.FLOATBUFFER.clear();
//				if ((rg.flag & 16) == 16)
//				{
//					RenderO.FLOATBUFFER.put(0.0F);
//					RenderO.FLOATBUFFER.put(0.0F);
//				}
//				else
//				{
//					RenderO.FLOATBUFFER.put(drawda.light_b);
//					RenderO.FLOATBUFFER.put(drawda.light_s);
//				}
//				RenderO.FLOATBUFFER.flip();
//				OpenGlHelper.glUniform2(rs.uniformlocation_int_array[3], RenderO.FLOATBUFFER);
//
//				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, drawda.light_b, drawda.light_s);
//
//				if ((drawmap.extra_bit & 2) == 2)
//				{
//					RenderS.setFloatBuffer(KEY_FLOAT_ARRAY_LIST.get(integer));
//					OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[6], false, RenderS.BONE_FLOATBUFFER);
//				}
//
//				if (NaliConfig.VAO)
//				{
//					NaliGL.glDrawElementsTUi0(rg.index_length);
//				}
//				else
//				{
//					GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
//				}
//			}

			endBuffer(rs);
//			if (!NaliConfig.VAO)
//			{
//				RenderO.disableBuffer(rs);
//			}
		}
	}

	public static void startBuffer(DrawMap drawmap, MemoG rg, MemoS rs)
	{
		RenderO.enableBuffer(rg, rs);

		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 1);
		if ((drawmap.extra_bit & DrawMap.B_COLOR) == 0)
		{
//				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//				Nali.warn("texture " + ByteReader.getInt(drawmap, 4));
//				Nali.warn("texture_state " + (rg.state & 1));
//				RenderO.setTextureBuffer(drawmap.texture, (byte)(rg.flag & 1+2));
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, drawmap.texture);
		}
		else
		{
			int color = drawmap.texture;
			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
			RenderO.FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
			RenderO.FLOATBUFFER.put((color & 0xFF) / 255.0F);
			RenderO.FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
			RenderO.FLOATBUFFER.flip();
			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[5], RenderO.FLOATBUFFER);
		}
	}

	public static void render(Integer integer, DrawMap drawmap, MemoG rg, MemoS rs)
	{
		DrawDa drawda = DRAWDA_LIST.get(integer);
//				Nali.warn("g" + g + " i" + integer);

		RenderO.FLOATBUFFER.limit(16);
		MemoA.put(RenderO.FLOATBUFFER, drawda.projection_m4x4_float, 16);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, RenderO.FLOATBUFFER);
		MemoA.put(RenderO.FLOATBUFFER, drawda.modelview_m4x4_float, 16);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, RenderO.FLOATBUFFER);
		RenderO.FLOATBUFFER.limit(4);
		MemoA.put(RenderO.FLOATBUFFER, drawda.color_v4_float, 4);
		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], RenderO.FLOATBUFFER);
//				MemoA.put(RenderO.FLOATBUFFER, drawda.light0position_v4_float, 4);
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], RenderO.FLOATBUFFER);

		RenderO.FLOATBUFFER.clear();
		if ((rg.state & MemoG.B_GLOW) == 0)
		{
			RenderO.FLOATBUFFER.put(drawda.light_b);
			RenderO.FLOATBUFFER.put(drawda.light_s);
		}
		else
		{
			RenderO.FLOATBUFFER.put(0.0F);
			RenderO.FLOATBUFFER.put(0.0F);
		}
		RenderO.FLOATBUFFER.flip();
		OpenGlHelper.glUniform2(rs.uniformlocation_int_array[3], RenderO.FLOATBUFFER);

		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, drawda.light_b, drawda.light_s);

		if ((drawmap.extra_bit & DrawMap.B_SKINNING) == DrawMap.B_SKINNING)
		{
			RenderS.setFloatBuffer(KEY_FLOAT_ARRAY_LIST.get(integer));
			OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[6], false, RenderS.BONE_FLOATBUFFER);
		}

		if (NaliConfig.VAO)
		{
			NaliGL.glDrawElementsTUi0(rg.index_length);
		}
		else
		{
			GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
		}
	}

	public static void endBuffer(MemoS rs)
	{
		if (!NaliConfig.VAO)
		{
			RenderO.disableBuffer(rs);
		}
	}
}
