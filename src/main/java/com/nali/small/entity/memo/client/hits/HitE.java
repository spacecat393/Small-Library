package com.nali.small.entity.memo.client.hits;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
    public abstract class HitE<RG extends MemoGo, RS extends MemoSo, RC extends ClientDaSn, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, BD extends IBothDaSe, E extends Entity, I extends IMixE<BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, C>, C extends ClientE<RG, RS, RC, RST, R, BD, E, I, M>>
{
    public C c;

    public HitE(C c)
    {
        this.c = c;
    }

    public abstract void run(E player_entity, AxisAlignedBB axisalignedbb);
    public abstract boolean should(E player_entity, AxisAlignedBB axisalignedbb);
}
