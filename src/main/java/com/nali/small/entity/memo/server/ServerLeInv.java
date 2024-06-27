package com.nali.small.entity.memo.server;

import com.nali.data.IBothDaE;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import com.nali.small.entity.memo.IBothLeInv;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundN;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.EntityDataManager;

import static com.nali.small.entity.EntityLeInv.MOUTH_ITEMSTACK_DATAPARAMETER;

public abstract class ServerLeInv<SD extends ISoundN, BD extends IBothDaE, E extends EntityLeInv, I extends IMixLe<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerLe<SD, BD, E, I, A> implements IBothLeInv<SD, BD, E, I>
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
        IBothLeInv.super.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        IBothLeInv.super.readEntityFromNBT(nbttagcompound);
    }

    @Override
    public Inventory getInventory()
    {
        return this.inventory;
    }
}
