package com.nali.small.render;

import com.nali.small.data.BoxData;
import com.nali.list.items.SmallBox;
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
        SmallBox.OBJECTRENDER = new BoxRender(new BoxData(), DATALOADER);
        SmallBox.OBJECTRENDER.sx = s;
        SmallBox.OBJECTRENDER.sy = s;
        SmallBox.OBJECTRENDER.sz = s;
        SmallBox.OBJECTRENDER.z = 0.0F;
    }
}
