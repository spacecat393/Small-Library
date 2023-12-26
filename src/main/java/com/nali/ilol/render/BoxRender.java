package com.nali.ilol.render;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.system.DataLoader;

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
