package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxRender extends ObjectRender
{
    public BoxRender(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);
        this.texture_index_int_array[0] = 0;
        this.texture_index_int_array[1] = 1;
        this.texture_index_int_array[2] = 1;
    }
}
