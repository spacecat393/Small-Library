package com.nali.ilol.render;

import com.nali.data.BothData;
import com.nali.render.SkinningRender;
import com.nali.system.DataLoader;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

//        int brightness = this.entity.getBrightnessForRender();
        int brightness = 0;
        World world = this.entity.getEntityWorld();
        BlockPos blockpos = this.entity.getPosition();

        if (world.isBlockLoaded(blockpos))
        {
            brightness = world.getCombinedLight(blockpos, 0);
        }

        this.lig_b = brightness & 0xFFFF;
        this.lig_s = (brightness >> 16) & 0xFFFF;
//        this.lig_b = brightness % 65536;
//        this.lig_s = brightness / 65536.0F;
    }
}
