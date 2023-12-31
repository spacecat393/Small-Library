package com.nali.ilol.render;

import com.nali.data.BothData;
import com.nali.render.SkinningRender;
import com.nali.system.DataLoader;
import net.minecraft.entity.Entity;

public class SkinningEntitiesRender extends SkinningRender
{
    public Entity entity;

    public SkinningEntitiesRender(BothData bothdata, DataLoader dataloader, Entity entity)
    {
        super(bothdata, dataloader);
        this.entity = entity;
    }

    @Override
    public void updateLightCoord()
    {
        super.updateLightCoord();

        int brightness = this.entity.getBrightnessForRender();
        this.lig_b = brightness & 0xFFFF;
        this.lig_s = (brightness >> 16) & 0xFFFF;
    }
}
