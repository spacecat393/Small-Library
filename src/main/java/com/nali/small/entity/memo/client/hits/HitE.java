package com.nali.small.entity.memo.client.hits;

import com.nali.data.IBothDaE;
import com.nali.data.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.small.entity.memo.client.render.IRender;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
    public abstract class HitE<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, IR extends IRender, M extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, IR, C>, C extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, M, IR>>
{
    public C c;

    public HitE(C c)
    {
        this.c = c;
    }

    public abstract void run(Entity player_entity, AxisAlignedBB axisalignedbb);
    public abstract boolean should(Entity player_entity, AxisAlignedBB axisalignedbb);
}
