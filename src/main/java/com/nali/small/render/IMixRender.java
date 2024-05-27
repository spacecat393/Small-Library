package com.nali.small.render;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMixRender
{
    @SideOnly(Side.CLIENT)
    ObjectRender getObjectRender();
    @SideOnly(Side.CLIENT)
    DrawScreen getDrawScreen();

    @SideOnly(Side.CLIENT)
    default void render()
    {
        this.getDrawScreen().render(this.getObjectRender());
    }
}
