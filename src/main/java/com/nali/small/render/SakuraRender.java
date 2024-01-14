package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.system.DataLoader;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;

public class SakuraRender extends ObjectRender
{
    public SakuraRender(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);
        this.texture_index_int_array[0] = 2;
        this.texture_index_int_array[1] = 3;
        float s = -5.0F;
        this.sx = s;
        this.sy = s;
        this.sz = s;
    }

    @Override
    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }

    @Override
    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }
}
