package com.nali.list.render;

import com.nali.data.BothData;
import com.nali.data.client.ClientData;
import com.nali.small.data.both.TPBaseBothData;
import com.nali.small.data.client.TPBaseClientData;
import com.nali.small.render.SmallSkinningRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.OBJECT_LIST;

@SideOnly(Side.CLIENT)
public class TPBaseRender extends SmallSkinningRender
{
    public static ClientData CLIENTDATA = new TPBaseClientData();
    public static BothData BOTHDATA = new TPBaseBothData();
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
