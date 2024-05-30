package com.nali.list.render;

import com.nali.data.BothData;
import com.nali.data.client.ClientData;
import com.nali.small.data.both.TPBaseBothData;
import com.nali.small.data.client.TPBaseClientData;
import com.nali.small.render.SmallSkinningBlocksRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.OBJECT_LIST;

@SideOnly(Side.CLIENT)
public class TPBaseRender extends SmallSkinningBlocksRender
{
    public static ClientData CLIENTDATA = new TPBaseClientData();
    public static BothData BOTHDATA = new TPBaseBothData();
    public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();
//    static
//    {
//        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart())).element_array_buffer, 0);
//        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, 0);
//        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 2)).element_array_buffer, 0);
//    }

    public TPBaseRender(Block block)
    {
        super(null, BOTHDATA, CLIENTDATA, block);
    }

    public static void setTextureMap(/*TextureManager texturemanager*/)
    {
//        Nali.LOGGER.info("render! this "/* + texturemanager*/);
//        Nali.LOGGER.info(TEXTURE_MAP != null);
//        Nali.LOGGER.info(CLIENTDATA != null);
//        Nali.LOGGER.info(OBJECT_LIST.size());
        int diamond_block = com.nali.render.RenderHelper.getTextureBuffer(/*texturemanager, */new ResourceLocation("textures/blocks/diamond_block.png"));
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart())).element_array_buffer, com.nali.render.RenderHelper.getTextureBuffer(/*texturemanager, */new ResourceLocation("textures/blocks/obsidian.png")));
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 1)).element_array_buffer, diamond_block);
        TEXTURE_MAP.put(((OpenGLObjectMemory)OBJECT_LIST.get(CLIENTDATA.StartPart() + 2)).element_array_buffer, diamond_block);
//        Nali.LOGGER.info("end");
    }

//    @Override
//    public int getTextureID(OpenGLObjectMemory openglobjectmemory)
//    {
//        Integer integer = TEXTURE_MAP.get(openglobjectmemory.element_array_buffer);
//        if (integer == null)
//        {
//            return super.getTextureID(openglobjectmemory);
//        }
//        else
//        {
//            return integer;
//        }
//    }

    @Override
    public int getTextureBuffer(OpenGLObjectMemory openglobjectmemory)
    {
        return TEXTURE_MAP.get(openglobjectmemory.element_array_buffer);
//        Integer integer = TEXTURE_MAP.get(openglobjectmemory.element_array_buffer);
//        if (integer == null)
//        {
//            return super.getTextureBuffer(openglobjectmemory);
//        }
//        else
//        {
//            return integer;
//        }
    }
}
