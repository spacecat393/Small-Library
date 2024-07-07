package com.nali.small.mix.memo.client;

import com.nali.da.client.IClientDaO;
import com.nali.draw.DrawScreen;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothI;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientI<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaO, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, D extends DrawScreen<RG, RS, RST, RC, R>, I extends IMixN<?, E>, E extends Item> extends ClientN<RG, RS, RC, RST, R, D, I, E> implements IBothI<E>
{
    public ClientI(R r, D d, I i)
    {
        super(r, d, i);
    }
}
