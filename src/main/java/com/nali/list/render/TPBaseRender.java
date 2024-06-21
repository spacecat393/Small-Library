package com.nali.list.render;

import com.nali.data.BothDataS;
import com.nali.data.client.ClientDataO;
import com.nali.small.data.both.TPBaseBothDataS;
import com.nali.small.data.client.TPBaseClient;
import com.nali.small.render.SmallSkinningRender;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class TPBaseRender extends SmallSkinningRender
{
    public static ClientDataO CLIENTDATA = new TPBaseClient();
    public static BothDataS BOTHDATA = new TPBaseBothDataS();
    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public TPBaseRender()
    {
        super(null, BOTHDATA, CLIENTDATA);
    }

    public static void setTextureMap()
    {
        int diamond_block = com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png"));
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart())).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, diamond_block);
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 2)).element_array_buffer, diamond_block);
    }

    @Override
    public int getTextureBuffer(OpenGLObjectMemory openglobjectmemory)
    {
        return TEXTURE_MAP.get(openglobjectmemory.element_array_buffer);
    }
}
