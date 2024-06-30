package com.nali.small.entity.memo;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.render.RenderE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBothE<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>>
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

//    void add();
    void remove();

    @SideOnly(Side.CLIENT)
    void setShouldRender(boolean result);
    @SideOnly(Side.CLIENT)
    void doRender(RenderE<E> rendere, double ox, double oy, double oz, float partialTicks);

    @SideOnly(Side.CLIENT)
    void playSound(int i);

    I getI();
}
