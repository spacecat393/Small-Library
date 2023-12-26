package com.nali.ilol.render;

import com.nali.ilol.data.BoxData;
import com.nali.list.items.IlolBox;
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
        IlolBox.I.objectrender = new BoxRender(new BoxData(), DATALOADER);
        IlolBox.I.objectrender.sx = s;
        IlolBox.I.objectrender.sy = s;
        IlolBox.I.objectrender.sz = s;
        IlolBox.I.objectrender.z = 0.0F;
    }
}
