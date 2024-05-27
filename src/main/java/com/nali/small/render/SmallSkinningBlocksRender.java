package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.data.client.ClientData;
import com.nali.render.EntitiesRenderMemory;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallSkinningBlocksRender extends SkinningBlocksRender
{
    public SmallSkinningBlocksRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, ClientData clientdata, Block block)
    {
        super(entitiesrendermemory, bothdata, clientdata, block);
    }

    @Override
    public int getTextureID(OpenGLObjectMemory openglobjectmemory)
    {
        return TEXTURE_STEP + super.getTextureID(openglobjectmemory);
    }

    @Override
    public int getShaderID(OpenGLObjectMemory openglobjectmemory)
    {
        return SHADER_STEP + super.getShaderID(openglobjectmemory);
    }
}
