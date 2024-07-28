package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.EntityE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientEInv<RC extends IClientDaO, R extends RenderO<RC>, SD, BD extends IBothDaNe, E extends EntityE, I extends IMixE<SD, BD, E>, MB extends MixBoxE<RC, R, SD, BD, E, I, MR, ?>, MR extends MixRenderE<RC, R, SD, BD, E, I, MB, ?>> extends ClientE<RC, R, SD, BD, E, I, MB, MR> implements IBothEInv<SD, BD, E, I>
{
    public Inventory inventory;

    public ClientEInv(I i, R r, Inventory inventory)
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
