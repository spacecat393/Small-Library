package com.nali.small.entity.memo.client.render.mix;

import com.nali.data.IBothDaNe;
import com.nali.data.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class MixRenderE<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, ?, C>, C extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, MB, ?>>
{
    public C c;

    public float shadow_opaque, shadow_size;

    public MixRenderE(C c)
    {
        this.c = c;
    }

    public abstract void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks);
}
