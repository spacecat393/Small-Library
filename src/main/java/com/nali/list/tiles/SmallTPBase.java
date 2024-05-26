package com.nali.list.tiles;

import com.nali.draw.DrawScreen;
import com.nali.list.render.BoxRender;
import com.nali.render.ObjectRender;
import com.nali.small.render.IMixRender;
import net.minecraft.tileentity.TileEntity;

public class SmallTPBase extends TileEntity implements IMixRender
{
    @Override
    public ObjectRender getObjectRender()
    {
        return new BoxRender();
    }

    @Override
    public DrawScreen getDrawScreen()
    {
        return null;
    }
}
