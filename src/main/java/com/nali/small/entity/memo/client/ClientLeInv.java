package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLeInv<RC extends IClientDaS, R extends RenderO<RC>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MB extends MixBoxE<RC, R, SD, BD, E, I, MR, ?>, MR extends MixRenderE<RC, R, SD, BD, E, I, MB, ?>> extends ClientLe<RC, R, SD, BD, E, I, MB, MR> implements IBothLeInv<SD, BD, E, I>
{
    public Inventory inventory;

    public ClientLeInv(I i, R r, Inventory inventory)
    {
        super(i, r);
        this.inventory = inventory;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }
}
