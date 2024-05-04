package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.data.client.ClientData;
import com.nali.render.EntitiesRenderMemory;
import com.nali.render.SkinningRender;
import com.nali.system.DataLoader;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkinningEntitiesRender extends SkinningRender
{
    public Entity entity;

    public SkinningEntitiesRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, ClientData clientdata, DataLoader dataloader, Entity entity)
    {
        super(entitiesrendermemory, bothdata, clientdata, dataloader);
        this.entity = entity;
    }

//    @Override
    public void updateLightCoord()
    {
//        super.updateLightCoord();

//        int brightness = this.entity.getBrightnessForRender();
//        int brightness = 0;
        if (this.entity.isBurning())
        {
            this.lig_b = -1.0F;
            this.lig_s = -1.0F;
            return;
        }

        World world = this.entity.world;
        BlockPos blockpos = this.entity.getPosition();

        if (world.isBlockLoaded(blockpos))
        {
//            brightness = world.getCombinedLight(blockpos, 0);
            this.lig_b = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
            this.lig_s = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;
        }

        if (this.lig_b < 0.1875F)
        {
            this.lig_b = 0.1875F;
        }
//        this.lig_b = brightness % 65536;
//        this.lig_s = brightness / 65536.0F;
    }
}
