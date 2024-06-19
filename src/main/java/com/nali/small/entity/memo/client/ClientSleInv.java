package com.nali.small.entity.memo.client;

import com.nali.render.SkinningRender;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.client.mixbox.MixBoxSe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSleInv<R extends SkinningRender, E extends EntityLivingBase, I extends IMixLe<E>, M extends MixBoxSe<R, E, I, ?>> extends ClientSle<R, E, I, M> implements IBothLeInv<E, I>
{
    public Inventory inventory;

    public ClientSleInv(I i, M m)
    {
        super(i, m);
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }
}
