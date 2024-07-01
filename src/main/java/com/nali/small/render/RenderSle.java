package com.nali.small.render;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSle<E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MB extends MixBoxSle<RG, RS, RC, RST, ?, SD, BD, E, I, MR, C>, MR extends MixRenderE<RG, RS, RC, RST, ?, SD, BD, E, I, MB, C>, C extends ClientSle<RG, RS, RC, RST, ?, SD, BD, E, I, MB, MR>, SD extends ISoundLe, BD extends IBothDaSe<SD>, RG extends MemoGs, RS extends MemoSs, RST extends StoreS<RG, RS>, RC extends IClientDaS> extends RenderS<SD, BD, RG, RS, RST, RC>
{
    public C c;

    public RenderSle(RST rst, RC rc, BD bd, C c)
    {
        super(rst, rc, bd);
        this.c = c;
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
