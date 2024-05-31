package com.nali.small.render;

import com.nali.data.BothData;
import com.nali.data.client.ClientData;
import com.nali.render.EntitiesRenderMemory;
import com.nali.render.SkinningRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkinningEntitiesRender extends SkinningRender
{
    public Entity entity;

    public SkinningEntitiesRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, ClientData clientdata/*, DataLoader dataloader*/, Entity entity)
    {
        super(entitiesrendermemory, bothdata, clientdata/*, dataloader*/);
        this.entity = entity;
    }

    public void updateLightCoord()
    {
        if (this.entity.isBurning())
        {
            this.lig_b = -1.0F;
            this.lig_s = -1.0F;
            return;
        }

        this.updateLightCoord(this.entity.world, this.entity.getPosition());
    }

    @Override
    public boolean getTransparent(OpenGLObjectMemory openglobjectmemory)
    {
        return this.entity == null || this.entity.isInvisible() || this.entity.isInvisibleToPlayer(Minecraft.getMinecraft().player) || super.getTransparent(openglobjectmemory);
    }
}
