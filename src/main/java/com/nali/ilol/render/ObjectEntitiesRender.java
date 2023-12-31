package com.nali.ilol.render;

import com.nali.data.BothData;
import com.nali.render.ObjectRender;
import com.nali.system.DataLoader;
import net.minecraft.entity.Entity;

public class ObjectEntitiesRender extends ObjectRender
{
    public Entity entity;

    public ObjectEntitiesRender(BothData bothdata, DataLoader dataloader, Entity entity)
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
