package com.nali.small.render;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.IClientS;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSe<E extends Entity, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RC, ?, SD, BD, E, I, MR, C>, MR extends MixRenderSe<RC, ?, SD, BD, E, I, MB, C>, C extends ClientE<RC, ?, SD, BD, E, I, MB, MR> & IClientS<RC, ?, SD, BD, E, I, MR>, SD, BD extends IBothDaNe & IBothDaSn, RC extends IClientDaS> extends RenderS<BD, RC>
{
    public C c;

    public RenderSe(RC rc, BD bd)
    {
        super(rc, bd);
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
    public boolean getTransparent(MemoG rg)
    {
        boolean result = super.getTransparent(rg);
        I i = this.c.i;
        if (i == null)
        {
            return result;
        }

        E e = i.getE();
        return result/* || e == null*/ || e.isInvisible() || e.isInvisibleToPlayer(Minecraft.getMinecraft().player);
    }
}
