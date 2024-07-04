package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaNe;
import com.nali.data.client.IClientDaS;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.sound.ISoundLe;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLeInv<RG extends MemoGo, RS extends MemoSo, RC extends IClientDaS, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, MB extends MixBoxE<RG, RS, RC, RST, R, SD, BD, E, I, MR, ?>, MR extends MixRenderE<RG, RS, RC, RST, R, SD, BD, E, I, MB, ?>> extends ClientLe<RG, RS, RC, RST, R, SD, BD, E, I, MB, MR> implements IBothLeInv<SD, BD, E, I>
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
