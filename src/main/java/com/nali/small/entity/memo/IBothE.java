package com.nali.small.entity.memo;

import com.nali.small.entity.IMixE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;

public interface IBothE<E extends Entity, I extends IMixE<E>>
{
    default boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        entityplayer.swingArm(enumhand);
        return true;
    }

    default boolean isMove()
    {
        return !this.getI().getE().isDead;
    }

    void onUpdate();
    void writeEntityToNBT(NBTTagCompound nbttagcompound);
    void readEntityFromNBT(NBTTagCompound nbttagcompound);

    I getI();
}
