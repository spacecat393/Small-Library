package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.system.DataLoader;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SakuraRender extends ObjectRender
{
    public SakuraRender(BothData bothdata, DataLoader dataloader)
    {
        super(null, bothdata, dataloader);
        this.texture_index_int_array[0] = 2;
        this.texture_index_int_array[1] = 3;
        float s = -5.0F;
        this.objectscreendraw.sx = s;
        this.objectscreendraw.sy = s;
        this.objectscreendraw.sz = s;
    }

    @Override
    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }
}
