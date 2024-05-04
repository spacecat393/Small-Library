package com.nali.small.render.object;

import com.nali.data.client.ClientData;
import com.nali.render.ObjectRender;
import com.nali.small.data.client.SakuraClientData;
import com.nali.small.render.RenderHelper;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SakuraRender extends ObjectRender
{
//    public static int ID;
//    public static DataLoader DATALOADER = RenderHelper.DATALOADER;
    public static ClientData CLIENTDATA = new SakuraClientData();

    public SakuraRender()
    {
        super(null, CLIENTDATA, RenderHelper.DATALOADER);
//        float s = -5.0F;
//        this.objectscreendraw.sx = s;
//        this.objectscreendraw.sy = s;
//        this.objectscreendraw.sz = s;
    }

    @Override
    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }
}
