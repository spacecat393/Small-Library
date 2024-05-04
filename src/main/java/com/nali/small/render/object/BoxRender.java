package com.nali.small.render.object;

import com.nali.data.client.ClientData;
import com.nali.render.ObjectRender;
import com.nali.small.data.client.BoxClientData;
import com.nali.small.render.RenderHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxRender extends ObjectRender
{
//    public static int ID;
//    public static DataLoader DATALOADER = RenderHelper.DATALOADER;
    public static ClientData CLIENTDATA = new BoxClientData();

    public BoxRender()
    {
        super(null, CLIENTDATA, RenderHelper.DATALOADER);
    }
}
