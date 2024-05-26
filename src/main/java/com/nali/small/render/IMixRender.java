package com.nali.small.render;

import com.nali.draw.DrawScreen;
import com.nali.render.ObjectRender;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IMixRender
{
    ObjectRender getObjectRender();
    DrawScreen getDrawScreen();

    default void render()
    {
        this.getDrawScreen().render(this.getObjectRender());
    }
}
