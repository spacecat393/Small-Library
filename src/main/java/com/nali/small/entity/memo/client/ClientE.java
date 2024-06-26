package com.nali.small.entity.memo.client;

import com.nali.data.IBothDaSe;
import com.nali.data.client.ClientDaSn;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.OpenInvGUI;
import com.nali.network.NetworkRegistry;
import com.nali.render.RenderO;
import com.nali.render.sound.SoundRender;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.client.mixbox.MixBoxE;
import com.nali.system.bytes.BytesWriter;
import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import com.nali.system.opengl.store.StoreO;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public abstract class ClientE<RG extends MemoGo, RS extends MemoSo, RC extends ClientDaSn, RST extends StoreO<RG, RS>, R extends RenderO<RG, RS, RST, RC>, BD extends IBothDaSe, E extends Entity, I extends IMixE<BD, E>, M extends MixBoxE<RG, RS, RC, RST, R, BD, E, I, ?>> implements IBothE<BD, E, I>
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
                NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
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
        this.r.c.scale = this.i.getBD().Scale();
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
