package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.INNeInv;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSeInv<RG extends MemoGs, RS extends MemoSs, RC extends ClientDaSn, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, BD extends IBothDaSe, E extends Entity, I extends IMixE<BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, ?>> extends ClientE<RG, RS, RC, RST, R, BD, E, I, M> implements INNeInv<BD, E/*, I*/>
{
    public Inventory inventory;

    public ClientSeInv(I i, M m)
    {
        super(i, m);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        INNeInv.super.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        INNeInv.super.readEntityFromNBT(nbttagcompound);
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
