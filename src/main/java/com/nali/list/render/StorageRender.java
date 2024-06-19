package com.nali.list.render;

import com.nali.data.client.ClientData;
import com.nali.small.data.client.StorageClient;
import com.nali.small.render.SmallObjectRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.OBJECT_LIST;

@SideOnly(Side.CLIENT)
public class StorageRender extends SmallObjectRender
{
    public static ClientData CLIENTDATA = new StorageClient();
    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

    public StorageRender()
    {
        super(null, CLIENTDATA);
    }

    public static void setTextureMap()
    {
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart())).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/diamond_block.png")));
    }

    @Override
    public int getTextureBuffer(OpenGLObjectMemory openglobjectmemory)
    {
        return TEXTURE_MAP.get(openglobjectmemory.element_array_buffer);
    }
}