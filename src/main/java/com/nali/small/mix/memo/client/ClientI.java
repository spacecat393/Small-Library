package com.nali.small.mix.memo.client;

import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.IBothI;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientI<RC extends IClientDaO, R extends RenderO<RC>, I extends IMixN<?, E>, E extends Item> extends ClientN<RC, R, I, E> implements IBothI<E>
{
    public ClientI(R r, I i)
    {
        super(r, i);
    }
}
