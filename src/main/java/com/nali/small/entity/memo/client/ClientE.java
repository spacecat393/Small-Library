package com.nali.small.entity.memo.client;

import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.OpenInvGUI;
import com.nali.networks.NetworksRegistry;
import com.nali.render.ObjectRender;
import com.nali.render.SoundRender;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public abstract class ClientE<R extends ObjectRender, E extends Entity, I extends IMixE<E>, M extends MixBoxE<R, E, I, ?>> implements IBothE<E, I>
{
    public I i;
    public R r;
    public M m;

//    public IEMixBox iemixbox;

    public SoundRender soundrender;

    public UUID uuid;
    public boolean fake;

    public ClientE(I i, M m)
    {
        this.i = i;
        this.m = m;
        this.r = this.createObjectRender();
        this.soundrender = this.createSoundRender();
    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        if (entityplayer.isSneaking())
        {
            if (this.uuid != null)
            {
                byte[] byte_array = new byte[17];
                byte_array[0] = OpenInvGUI.ID;
                BytesWriter.set(byte_array, this.uuid, 1);
                NetworksRegistry.I.sendToServer(new ServerMessage(byte_array));
            }
        }
        else
        {
            this.m.checkAxisAlignedBB(entityplayer);
        }

        entityplayer.swingArm(enumhand);
        return true;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.initFakeFrame();
        this.r.entitiesrendermemory.scale = this.i.getBothData().Scale();
    }

    @Override
    public I getI()
    {
        return this.i;
    }

    public abstract void initFakeFrame();
    public abstract R createObjectRender();
    public abstract SoundRender createSoundRender();
//    public abstract IEMixBox createIEMixBox();
}
