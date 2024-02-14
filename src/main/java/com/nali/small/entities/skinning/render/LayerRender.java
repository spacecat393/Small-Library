package com.nali.small.entities.skinning.render;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerRender
{
    public SkinningEntities skinningentities;
    public LayerRender(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }
}
