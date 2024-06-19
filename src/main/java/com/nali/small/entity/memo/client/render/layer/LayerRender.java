package com.nali.small.entity.memo.client.render.layer;

import com.nali.render.ObjectRender;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerRender<R extends ObjectRender, E extends Entity, I extends IMixE<E>, C extends ClientE<R, E, I>>
{
    public C c;
    public LayerRender(C c)
    {
        this.c = c;
    }
}
