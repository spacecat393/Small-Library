package com.nali.small.render;

import com.nali.data.BothDataS;
import com.nali.data.client.ClientDataS;
import com.nali.render.SkinningRender;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreSle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSle<B extends BothDataS, G extends MemoGs, S extends MemoSs, ST extends StoreSle<G, S>, C extends ClientDataS> extends SkinningRender<B, G, S, ST, C>
{
    public Entity entity;

    public RenderSle(ST st, C c, B b, Entity entity)
    {
        super(st, c, b);
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
    public boolean getTransparent(G g)
    {
        return this.entity == null || this.entity.isInvisible() || this.entity.isInvisibleToPlayer(Minecraft.getMinecraft().player) || super.getTransparent(g);
    }
}
