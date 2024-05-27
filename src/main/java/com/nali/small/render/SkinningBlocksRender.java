package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.data.client.ClientData;
import com.nali.render.EntitiesRenderMemory;
import com.nali.render.SkinningRender;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkinningBlocksRender extends SkinningRender
{
    public Block block;

    public SkinningBlocksRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, ClientData clientdata/*, DataLoader dataloader*/, Block block)
    {
        super(entitiesrendermemory, bothdata, clientdata/*, dataloader*/);
        this.block = block;
    }

    public void updateLightCoord(World world, BlockPos blockpos)
    {
        if (world.isBlockLoaded(blockpos))
        {
            int brightness = world.getCombinedLight(blockpos, 0);
            this.lig_b = (brightness % 65536) / 255.0F;
            this.lig_s = (brightness / 65536.0F) / 255.0F;
        }

        if (this.lig_b < 0.1875F)
        {
            this.lig_b = 0.1875F;
        }
    }
}
