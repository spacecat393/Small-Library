package com.nali.small.entity.memo.client.box.hit;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class HitE<RC extends IClientDaO, R extends RenderO<RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MR extends MixRenderE<RC, R, SD, BD, E, I, MB, C>, MB extends MixBoxE<RC, R, SD, BD, E, I, MR, C>, C extends ClientE<RC, R, SD, BD, E, I, MB, MR>>
{
    public C c;

    public HitE(C c)
    {
        this.c = c;
    }

    public abstract void run(Entity player_entity, AxisAlignedBB axisalignedbb);
    public abstract boolean should(Entity player_entity, AxisAlignedBB axisalignedbb);
}
