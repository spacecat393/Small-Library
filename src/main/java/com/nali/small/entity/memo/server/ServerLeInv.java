package com.nali.small.entity.memo.server;

import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.INNeInv;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.EntityDataManager;

import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;

public abstract class ServerLeInv<E extends EntityLeInv<?>, I extends IMixLe<E>, A extends MixAIE<E, I, ?>> extends ServerLe<E, I, A> implements INNeInv<E, I>
{
    public Inventory inventory;

    public ServerLeInv(I i, A a)
    {
        super(i, a);
        this.inventory = new Inventory(1);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        ItemStack mouth_itemstack = this.inventory.offset_itemstack_nonnulllist.get(0);
        EntityDataManager entitydatamanager = this.i.getE().getDataManager();
        if (mouth_itemstack.isEmpty())
        {
            entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
        }
        else
        {
            entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, mouth_itemstack);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        INNeInv.super.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        INNeInv.super.readEntityFromNBT(nbttagcompound);
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
