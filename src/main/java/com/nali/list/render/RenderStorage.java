//package com.nali.list.render;
//
//import com.nali.da.IBothDaO;
//import com.nali.list.da.BothDaStorage;
//import com.nali.render.RenderHelper;
//import com.nali.small.render.SmallRenderO;
//import com.nali.system.opengl.memo.client.MemoG;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.nali.system.ClientLoader.G_LIST;
//
//@SideOnly(Side.CLIENT)
//public class RenderStorage
//<
//	BD extends IBothDaO
//> extends SmallRenderO<BD>
//{
//	public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();
//
//	public static void setTextureMap()
//	{
//		TEXTURE_MAP.clear();
//		TEXTURE_MAP.put(G_LIST.get(BothDaStorage.IDA.O_StartPart()).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
//		TEXTURE_MAP.put(G_LIST.get(BothDaStorage.IDA.O_StartPart() + 1).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png")));
//	}
//
//	@Override
//	public int getTextureBuffer(MemoG rg)
//	{
//		return TEXTURE_MAP.get(rg.ebo);
//	}
//
//	@Override
//	public byte getExtraBit(MemoG rg)
//	{
//		return (byte)(super.getExtraBit(rg) | 16);
//	}
//}