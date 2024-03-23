package com.nali.small.render;

import com.nali.list.items.SmallBox;
import com.nali.list.render.BoxRender;
import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHelper
{
    @SideOnly(Side.CLIENT)
    public static DataLoader DATALOADER = new DataLoader();

    public static void init()
    {
        float s = 0.25F;
        SmallBox.OBJECTRENDER = new BoxRender();
        SmallBox.OBJECTRENDER.objectscreendraw.sx = s;
        SmallBox.OBJECTRENDER.objectscreendraw.sy = s;
        SmallBox.OBJECTRENDER.objectscreendraw.sz = s;
        SmallBox.OBJECTRENDER.objectscreendraw.z = 0.0F;
    }
}
