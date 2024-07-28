package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.EntityE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSeInv<RC extends IClientDaS, R extends RenderS<BD, RC>, SD, BD extends IBothDaNe & IBothDaSn, E extends EntityE, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RC, R, SD, BD, E, I, MR, ?>, MR extends MixRenderSe<RC, R, SD, BD, E, I, MB, ?>> extends ClientSe<RC, R, SD, BD, E, I, MB, MR> implements IBothEInv<SD, BD, E, I>
{
    public Inventory inventory;

    public ClientSeInv(I i, R r, Inventory inventory)
    {
        super(i, r);
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
