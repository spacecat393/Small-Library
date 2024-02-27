package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.render.EntitiesRenderMemory;
import com.nali.render.SkinningRender;
import com.nali.system.DataLoader;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkinningEntitiesRender extends SkinningRender
{
    public Entity entity;

    public SkinningEntitiesRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, DataLoader dataloader, Entity entity)
    {
        super(entitiesrendermemory, bothdata, dataloader);
        this.entity = entity;
    }

    @Override
    public void updateLightCoord()
    {
        super.updateLightCoord();

//        int brightness = this.entity.getBrightnessForRender();
        int brightness = 0;
        World world = this.entity.world;
        BlockPos blockpos = this.entity.getPosition();

        if (world.isBlockLoaded(blockpos))
        {
            brightness = world.getCombinedLight(blockpos, 0);
        }

        this.objectworlddraw.lig_b = brightness & 0xFFFF;
        this.objectworlddraw.lig_s = (brightness >> 16) & 0xFFFF;
//        this.lig_b = brightness % 65536;
//        this.lig_s = brightness / 65536.0F;
    }
}
