package com.nali.list.render;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.small.data.BoxData;
import com.nali.small.render.RenderHelper;
import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxRender extends ObjectRender
{
    public static int ID;
    public static DataLoader DATALOADER = RenderHelper.DATALOADER;
    public static BothData BOTHDATA = new BoxData();

    public BoxRender()
    {
        super(null, BOTHDATA, DATALOADER, ID);
        this.texture_index_int_array[0] = 0;
        this.texture_index_int_array[1] = 1;
        this.texture_index_int_array[2] = 1;
    }
}
