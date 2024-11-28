//package com.nali.list.render;
//
//import com.nali.da.IBothDaO;
//import com.nali.list.da.BothDaSakura;
//import com.nali.small.render.SmallRenderO;
//import com.nali.system.opengl.memo.client.MemoG;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.nali.system.ClientLoader.G_LIST;
//
//@SideOnly(Side.CLIENT)
//public class RenderSakura
//<
//	BD extends IBothDaO
//> extends SmallRenderO<BD>
//{
//	public Map<Integer, Integer> color_map = new HashMap();//ebo hex
//	public byte extra_bit;
//
//	public RenderSakura()
//	{
////		float s = -5.0F;
////		this.objectscreendraw.sx = s;
////		this.objectscreendraw.sy = s;
////		this.objectscreendraw.sz = s;
//		color_map.put((G_LIST.get(BothDaSakura.IDA.O_StartPart())).ebo, 0xFFFFACDF);//ffd4e9
////		color_map.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).ebo, 0xFFffacdf);
//	}
//
//	@Override
//	public void setLightCoord(MemoS rs)
//	{
//	}
//
////	@Override
////	public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
////	{
////	}
//
//	@Override
//	public void setTextureUniform(MemoG rg, MemoS rs)
//	{
//		Integer integer = this.color_map.get(rg.ebo);
//		if (integer == null)
//		{
//			this.extra_bit = 0;
//			super.setTextureUniform(rg, rs);
//		}
//		else
//		{
//			this.extra_bit = 4;
//			int color = this.getTextureID(rg);
////			FLOATBUFFER.limit(3);
//			FLOATBUFFER.clear();
//			FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
//			FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
//			FLOATBUFFER.put((color & 0xFF) / 255.0F);
//			FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
//			FLOATBUFFER.flip();
//			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4/*+1*/], FLOATBUFFER);
//		}
//	}
//
//	@Override
//	public int getTextureID(MemoG rg)
//	{
//		Integer integer = this.color_map.get(rg.ebo);
//		if (integer == null)
//		{
//			this.extra_bit = 0;
//			return super.getTextureID(rg);
//		}
//		else
//		{
//			this.extra_bit = 4;
//			return integer;
//		}
//	}
//
//	@Override
//	public byte getExtraBit(MemoG rg)
//	{
//		return this.extra_bit;
//	}
//}
