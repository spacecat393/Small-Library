package com.nali.small.entity.memo.client.hits;

import com.nali.render.ObjectRender;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class HitE<R extends ObjectRender, E extends Entity, I extends IMixE<E>, M extends MixBoxE<R, E, I, C>, C extends ClientE<R, E, I, M>>
{
    public C c;

    public HitE(C c)
    {
        this.c = c;
    }

    public abstract void run(E player_entity, AxisAlignedBB axisalignedbb);
    public abstract boolean should(E player_entity, AxisAlignedBB axisalignedbb);
}
