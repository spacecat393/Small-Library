package com.nali.small.render;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.IClientS;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSe<E extends Entity, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RG, RS, RC, RST, ?, SD, BD, E, I, MR, C>, MR extends MixRenderE<RG, RS, RC, RST, ?, SD, BD, E, I, MB, C>, C extends ClientE<RG, RS, RC, RST, ?, SD, BD, E, I, MB, MR> & IClientS<RG, RS, RC, RST, ?, SD, BD, E, I>, SD, BD extends IBothDaNe & IBothDaSn, RG extends MemoGs, RS extends MemoSs, RST extends StoreS<RG, RS>, RC extends IClientDaS> extends RenderS<BD, RG, RS, RST, RC>
{
    public C c;

    public RenderSe(RST rst, RC rc, BD bd)
    {
        super(rst, rc, bd);
//        this.c = c;
    }

    public void updateLightCoord()
    {
        E e = this.c.i.getE();
        if (e.isBurning())
        {
            this.lig_b = -1.0F;
            this.lig_s = -1.0F;
            return;
        }

        this.updateLightCoord(e.world, e.getPosition());
    }

    @Override
    public boolean getTransparent(RG rg)
    {
        E e = this.c.i.getE();
        return e == null || e.isInvisible() || e.isInvisibleToPlayer(Minecraft.getMinecraft().player) || super.getTransparent(rg);
    }
}