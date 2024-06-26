package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.INNeInv;
import com.nali.small.entity.memo.client.mixbox.MixBoxSle;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import com.nali.system.opengl.store.StoreS;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientSleInv<RG extends MemoGs, RS extends MemoSs, RC extends ClientDaSn, RST extends StoreS<RG, RS>, R extends RenderS<BD, RG, RS, RST, RC>, BD extends IBothDaSe, E extends EntityLivingBase, I extends IMixLe<BD, E>, M extends MixBoxSle<RG, RS, RC, RST, R, BD, E, I, ?>> extends ClientSle<RG, RS, RC, RST, R, BD, E, I, M> implements INNeInv<BD, E/*, I*/>
{
    public Inventory inventory;

    public ClientSleInv(I i, M m)
    {
        super(i, m);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
//        super.writeEntityToNBT(nbttagcompound);
        INNeInv.super.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        INNeInv.super.readEntityFromNBT(nbttagcompound);
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }
}
