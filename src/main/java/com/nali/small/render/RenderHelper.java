package com.nali.small.render;

import com.nali.draw.DrawScreen;
import com.nali.list.items.SmallBox;
import com.nali.small.render.object.BoxRender;
import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHelper
{
    public static DataLoader DATALOADER = new DataLoader();

    public static void init()
    {
//        float s = 0.25F;
        SmallBox.OBJECTRENDER = new BoxRender();
        SmallBox.DRAWSCREEN = new DrawScreen();
//        SmallBox.OBJECTRENDER.objectscreendraw.sx = s;
//        SmallBox.OBJECTRENDER.objectscreendraw.sy = s;
//        SmallBox.OBJECTRENDER.objectscreendraw.sz = s;
//        SmallBox.OBJECTRENDER.objectscreendraw.z = 0.0F;
        SmallBox.DRAWSCREEN.scale(0.25F);
        SmallBox.DRAWSCREEN.z = 0.0F;
    }
}
