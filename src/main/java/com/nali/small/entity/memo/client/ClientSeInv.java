package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.small.entity.memo.client.render.IRender;
import com.nali.sound.ISoundN;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSeInv<RG extends MemoGs, RS extends MemoSs, RC extends IClientDaS, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, SD extends ISoundN, BD extends IBothDaSe<SD>, E extends Entity, I extends IMixE<SD, BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, IR, ?>, IR extends IRender> extends ClientE<RG, RS, RC, RST, R, SD, BD, E, I, M, IR> implements IBothEInv<SD, BD, E, I>
{
    public Inventory inventory;

    public ClientSeInv(I i, M m)
    {
        super(i, m);
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
