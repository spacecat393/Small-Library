package com.nali.small.render.object;

import com.nali.data.client.ClientData;
import com.nali.render.EntitiesRenderMemory;
import com.nali.render.ObjectRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.SHADER_STEP;
import static com.nali.list.data.SmallData.TEXTURE_STEP;

@SideOnly(Side.CLIENT)
public class SmallObjectRender extends ObjectRender
{
    public SmallObjectRender(EntitiesRenderMemory entitiesrendermemory, ClientData clientdata)
    {
        super(entitiesrendermemory, clientdata);
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
